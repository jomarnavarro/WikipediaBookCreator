/**
 * 
 */
package com.wikipedia.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.framework.util.Properties;
import com.wikipedia.pages.ArticlePage;

/**
 * This class tests creating wikipedia books and downloading a PDF file from it.
 * @author omar
 *
 */
public class WikipediaBookCreationTest extends BaseTest {
	
	/**
	 * creates a book with two topics, and downloads the created PDF. It also validates the PDF
	 * was downloaded succesfully.
	 * @param topic1
	 * @param topic2
	 */
	@Test
	@Parameters({"topic1", "topic2"})
	public void createTopicBook(String topic1, String topic2) {
		
		Assert.assertTrue(homePage.isAt());
		
		bookCreatorPage = homePage.createBook();
		
		Assert.assertTrue(bookCreatorPage.isAt());
		
		homePage = bookCreatorPage.startBookCreator();
		
		Assert.assertTrue(homePage.isAt());
		
		Assert.assertTrue(homePage.hasAddPageToBookLink());
		
		articlePage = homePage.searchTopic(topic1);
		
		Assert.assertTrue(articlePage.hasAddPageToBookLink());
		
		Assert.assertTrue(articlePage.isAt(topic1));
		
		articlePage = articlePage.addPageToBook();
		
		articlePage = articlePage.searchTopic(topic2);
		
		Assert.assertTrue(articlePage.hasAddPageToBookLink());
		
		Assert.assertTrue(articlePage.isAt(topic2));
		
		articlePage = articlePage.addPageToBook();
		
		bookManagementPage = articlePage.showBook();
		
		Assert.assertTrue(bookManagementPage.isAt());
		
		renderPage = bookManagementPage.nameBookAndDownload(Properties.BOOK_TITLE, Properties.BOOK_SUBTITLE);
		
		Assert.assertTrue(renderPage.isAt());
		
		exportPdfPage = renderPage.downloadFile();
		
		Assert.assertTrue(exportPdfPage.isAt());
		
		exportPdfPage.downloadPdf();		
		
	}
	
	
	
	

}
