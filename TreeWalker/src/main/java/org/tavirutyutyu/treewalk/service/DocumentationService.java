package org.tavirutyutyu.treewalk.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@Service
public class DocumentationService {
    private static final String GITHUB_RAW_URL = "https://raw.githubusercontent.com/Tavirutyutyu/TreeTraversal/master/README.md";


    public String getDocumentation() throws IOException {
        String markdown = fetchMarkdownFromGitHub();
        return wrapHtml(parseMarkdownToHtml(markdown));
    }

    private String fetchMarkdownFromGitHub() throws IOException{
        try (InputStream in = new URI(GITHUB_RAW_URL).toURL().openStream()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String parseMarkdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        Node document = parser.parse(markdown);
        return renderer.render(document);
    }

    private String wrapHtml(String bodyContent) {
        return "<!DOCTYPE html>" +
                "<html><head><meta charset='UTF-8'><title>Project Documentation</title></head>" +
                "<body style='max-width: 800px; margin: auto; font-family: sans-serif;'>" +
                bodyContent +
                "</body></html>";
    }
}
