// Genera la web de la documentación a partir de los boletines en Markdown.
//
// Lee todos los .md de docs/, crea un .html equivalente para cada uno y un
// index.html que enlaza a todos. El resultado se guarda en docs/site/.
// Si existe docs/img/, se copia a docs/site/img/ para que las imágenes
// enlazadas desde los .md (img/...) se vean también en la web.
//
// Uso (desde docs/):  npm ci && npm run build

import { readdir, readFile, writeFile, mkdir, rm, cp, stat } from "node:fs/promises";
import { dirname, join, basename } from "node:path";
import { fileURLToPath } from "node:url";
import { marked } from "marked";

const DOCS_DIR = dirname(fileURLToPath(import.meta.url));
const SITE_DIR = join(DOCS_DIR, "site");
const IMG_DIR = join(DOCS_DIR, "img");
const SITE_TITLE = "Prácticas continuas — Documentación";
const REPO_URL = "https://github.com/OlgaTYP/Practicas-continuas";

marked.use({ gfm: true });

const escapeHtml = (text) =>
  text.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;");

// Quita el formato Markdown básico de un texto (para títulos y descripciones).
const plainText = (md) =>
  md
    .replace(/\[([^\]]*)\]\([^)]*\)/g, "$1")
    .replace(/[`*_]/g, "")
    .trim();

// Título: primer encabezado "# ..."; si no hay, el nombre del fichero.
const extractTitle = (md, fallback) => {
  const match = md.match(/^#\s+(.+)$/m);
  return match ? plainText(match[1]) : fallback;
};

// Descripción para el índice: la cita "> ..." que sigue al título, si la hay.
const extractDescription = (md) => {
  for (const line of md.split(/\r?\n/)) {
    const text = line.trim();
    if (!text || /^#\s/.test(text)) continue;
    return text.startsWith(">") ? plainText(text.replace(/^>\s*/, "")) : "";
  }
  return "";
};

// Los enlaces entre boletines (boletin2.md, boletin2.md#seccion) apuntan al .html generado.
const rewriteMdLinks = (html) =>
  html.replace(/href="(?!https?:\/\/)([^"#]+)\.md(#[^"]*)?"/g, 'href="$1.html$2"');

// Orden natural: boletin1A, boletin1B, boletin2, ..., boletin10.
const naturalCompare = (a, b) => a.localeCompare(b, "es", { numeric: true, sensitivity: "base" });

const STYLES = `
:root {
  --bg: #ffffff; --fg: #1f2328; --muted: #59636e; --border: #d1d9e0;
  --accent: #0969da; --code-bg: #f6f8fa; --card-bg: #f6f8fa; --header-bg: #24292f; --header-fg: #ffffff;
}
@media (prefers-color-scheme: dark) {
  :root {
    --bg: #0d1117; --fg: #e6edf3; --muted: #9198a1; --border: #3d444d;
    --accent: #4493f8; --code-bg: #151b23; --card-bg: #151b23; --header-bg: #010409; --header-fg: #e6edf3;
  }
}
* { box-sizing: border-box; }
body {
  margin: 0; background: var(--bg); color: var(--fg);
  font: 16px/1.6 -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
}
header.site {
  background: var(--header-bg); color: var(--header-fg); padding: 12px 16px;
}
header.site .wrap { display: flex; flex-wrap: wrap; gap: 8px 24px; align-items: center; justify-content: space-between; }
header.site a { color: var(--header-fg); text-decoration: none; font-weight: 600; }
header.site nav { display: flex; flex-wrap: wrap; gap: 4px 16px; font-size: 14px; }
header.site nav a { font-weight: 400; opacity: .85; }
header.site nav a:hover, header.site nav a[aria-current="page"] { opacity: 1; text-decoration: underline; }
.wrap { max-width: 980px; margin: 0 auto; }
main { padding: 24px 16px 48px; }
a { color: var(--accent); }
h1, h2, h3, h4 { line-height: 1.25; margin: 1.6em 0 .6em; }
h1 { font-size: 2em; border-bottom: 1px solid var(--border); padding-bottom: .3em; margin-top: 0; }
h2 { font-size: 1.5em; border-bottom: 1px solid var(--border); padding-bottom: .3em; }
blockquote { margin: 0 0 16px; padding: 0 1em; color: var(--muted); border-left: 4px solid var(--border); }
code { font-family: ui-monospace, SFMono-Regular, Consolas, "Liberation Mono", monospace; font-size: 85%;
  background: var(--code-bg); padding: .2em .4em; border-radius: 6px; }
pre { background: var(--code-bg); padding: 16px; border-radius: 6px; overflow-x: auto; line-height: 1.45; }
pre code { background: none; padding: 0; font-size: 85%; }
table { border-collapse: collapse; display: block; width: max-content; max-width: 100%; overflow-x: auto; margin: 0 0 16px; }
th, td { border: 1px solid var(--border); padding: 6px 13px; vertical-align: top; }
th { background: var(--code-bg); }
img { max-width: 100%; height: auto; border: 1px solid var(--border); border-radius: 6px; }
hr { border: 0; border-top: 1px solid var(--border); margin: 24px 0; }
.pager { display: flex; justify-content: space-between; gap: 16px; margin-top: 48px; padding-top: 16px; border-top: 1px solid var(--border); }
.cards { list-style: none; padding: 0; display: grid; gap: 16px; grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); }
.cards li { background: var(--card-bg); border: 1px solid var(--border); border-radius: 8px; padding: 16px; }
.cards a { font-weight: 600; font-size: 1.1em; text-decoration: none; }
.cards a:hover { text-decoration: underline; }
.cards p { margin: 8px 0 0; color: var(--muted); font-size: 14px; }
footer { color: var(--muted); font-size: 14px; padding: 16px; border-top: 1px solid var(--border); text-align: center; }
`;

const layout = ({ title, body, pages, current }) => `<!doctype html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${escapeHtml(title)}</title>
<style>${STYLES}</style>
</head>
<body>
<header class="site">
  <div class="wrap">
    <a href="index.html">${escapeHtml(SITE_TITLE)}</a>
    <nav>
${pages
  .map(
    (p) =>
      `      <a href="${p.htmlName}"${p.htmlName === current ? ' aria-current="page"' : ""}>${escapeHtml(p.slug)}</a>`,
  )
  .join("\n")}
    </nav>
  </div>
</header>
<main class="wrap">
${body}
</main>
<footer>Generado a partir de <code>docs/*.md</code> · <a href="${REPO_URL}">${REPO_URL.replace("https://", "")}</a></footer>
</body>
</html>
`;

const exists = async (path) => {
  try {
    await stat(path);
    return true;
  } catch {
    return false;
  }
};

async function main() {
  const mdFiles = (await readdir(DOCS_DIR)).filter((f) => f.toLowerCase().endsWith(".md")).sort(naturalCompare);

  if (mdFiles.length === 0) {
    console.error("No se ha encontrado ningún .md en docs/");
    process.exit(1);
  }

  const pages = await Promise.all(
    mdFiles.map(async (file) => {
      const md = await readFile(join(DOCS_DIR, file), "utf8");
      const slug = basename(file, ".md");
      return {
        file,
        slug,
        htmlName: `${slug}.html`,
        md,
        title: extractTitle(md, slug),
        description: extractDescription(md),
      };
    }),
  );

  // Se regenera docs/site/ desde cero para no dejar páginas de .md ya borrados.
  await rm(SITE_DIR, { recursive: true, force: true });
  await mkdir(SITE_DIR, { recursive: true });

  for (const [i, page] of pages.entries()) {
    const prev = pages[i - 1];
    const next = pages[i + 1];
    const pager = `<nav class="pager">
  <span>${prev ? `<a href="${prev.htmlName}">← ${escapeHtml(prev.title)}</a>` : ""}</span>
  <span>${next ? `<a href="${next.htmlName}">${escapeHtml(next.title)} →</a>` : ""}</span>
</nav>`;
    const body = rewriteMdLinks(marked.parse(page.md)) + pager;
    await writeFile(
      join(SITE_DIR, page.htmlName),
      layout({ title: `${page.title} · ${SITE_TITLE}`, body, pages, current: page.htmlName }),
    );
    console.log(`  ${page.file} → site/${page.htmlName}`);
  }

  const indexBody = `<h1>${escapeHtml(SITE_TITLE)}</h1>
<p>Memorias de las sesiones de prácticas de la API de tareas (Spring Boot). Repositorio: <a href="${REPO_URL}">${REPO_URL.replace("https://", "")}</a>.</p>
<ul class="cards">
${pages
  .map(
    (p) => `  <li>
    <a href="${p.htmlName}">${escapeHtml(p.title)}</a>
    ${p.description ? `<p>${escapeHtml(p.description)}</p>` : ""}
  </li>`,
  )
  .join("\n")}
</ul>`;
  await writeFile(join(SITE_DIR, "index.html"), layout({ title: SITE_TITLE, body: indexBody, pages, current: "index.html" }));
  console.log("  index.html");

  if (await exists(IMG_DIR)) {
    await cp(IMG_DIR, join(SITE_DIR, "img"), { recursive: true });
    console.log("  img/ → site/img/");
  }

  console.log(`Web generada en ${SITE_DIR} (${pages.length} páginas + index.html)`);
}

main().catch((err) => {
  console.error(err);
  process.exit(1);
});
