import com.aspose.pdf.Document;
import com.aspose.pdf.HtmlLoadOptions;
import java.io.InputStream;
import java.net.URL;

public class Test {
  public static void main(String[] args) {
    try{
      URL oracleURL = new URL("https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html");

// Get web page as input stream
      InputStream is = oracleURL.openStream();
      // Initialize HTML load options
      HtmlLoadOptions htmloptions = new HtmlLoadOptions();

// Load stream into Document object
      Document pdfDocument = new Document(is, htmloptions);

// Save output as PDF format
      pdfDocument.save("HTML-to-PDF.pdf");

    } catch (Exception e){
      e.printStackTrace();
    }

  }
}
