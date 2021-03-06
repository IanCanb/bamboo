package bamboo.task;

import com.google.gson.Gson;
import com.lowagie.text.pdf.PdfReader;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TextExtractorTest {

    @Test
    public void testExtractPdfContent() throws IOException, TextExtractionException {
        Document doc = new Document();
        TextExtractor.extractPdfContent(new PdfReader(getClass().getResource("example.pdf")), doc);
        assertEquals("The title of a test PDF\n" +
                "This is a test PDF file. It was created by LibreOffice. ", doc.getText());
        assertEquals("The title field in the metadata", doc.getTitle());
    }

    @Test
    public void textExtractPdfBox() throws IOException, TextExtractionException {
        Document doc = new Document();
        try (InputStream stream = getClass().getResourceAsStream("example.pdf")) {
            TextExtractor.extractPdfBox(stream, doc);
        }
        assertEquals("The title of a test PDF" + System.lineSeparator()  +
                "This is a test PDF file. It was created by LibreOffice." + System.lineSeparator(), doc.getText());
        assertEquals("The title field in the metadata", doc.getTitle());
        assertEquals("The keywords field in the metadata", doc.getKeywords());
        assertEquals("The subject field in the metadata", doc.getCoverage());
    }

    @Test
    public void textExtractTika() throws IOException, TextExtractionException {
        Document doc = new Document();
        try (InputStream stream = getClass().getResourceAsStream("example.odt")) {
            TextExtractor.extractTika(stream, doc, URI.create("http://example.net/subdir/example.odt"));
        }
        assertEquals("Visible title\n" +
                "This is an example.\n", doc.getText());
        assertEquals("Metadata title", doc.getTitle());
    }

    @Test
    public void textExtractBadTitle() throws IOException, TextExtractionException {
        Document doc = new Document();
        try (InputStream stream = getClass().getResourceAsStream("badtitle.html")) {
            TextExtractor.extractTika(stream, doc, URI.create("http://example.net/subdir/badtitle.html"));
        }
        assertEquals("Ministerial Decision and Recommendations: New South Wales Ocean Trawl Fishery", doc.getTitle());
        assertEquals("Heading one!\n" +
                "\n" +
                "    Test\n" +
                "Link textHeading two!", doc.getText().trim());
        assertEquals("this is a description", doc.getDescription());
        assertEquals("this is keywords", doc.getKeywords());

        assertEquals("http://example.net/subdir/style.css", doc.getLinks().get(1).getUrl());
        assertEquals("site name", doc.getOgSiteName());
        assertEquals("og title", doc.getOgTitle());
        assertEquals(Arrays.asList("Heading one!", "Heading two!"), doc.getH1());
    }

    @Test
    public void testHackOffPandoraUrl() throws TextExtractionException {
        Document doc = new Document();
        TextExtractor.setUrls(doc, "http://pandora.nla.gov.au/pan/160553/20161116-1000/www.smh.com.au/money/super-and-funds/some-rare-good-financial-news-for-younger-people-20161109-gsm0lh.html");
        assertEquals("http://www.smh.com.au/money/super-and-funds/some-rare-good-financial-news-for-younger-people-20161109-gsm0lh.html", doc.getUrl());
        assertEquals("http://pandora.nla.gov.au/pan/160553/20161116-1000/www.smh.com.au/money/super-and-funds/some-rare-good-financial-news-for-younger-people-20161109-gsm0lh.html", doc.getDeliveryUrl());
    }
}
