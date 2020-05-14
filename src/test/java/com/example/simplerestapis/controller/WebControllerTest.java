package com.example.simplerestapis.controller;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import org.junit.Test;

import com.example.simplerestapis.controller.WebController.CSV;
import com.example.simplerestapis.controller.WebController.Movie;

public class WebControllerTest {

	@Test
	public void testGetBestMovies() throws IOException
	{
		WebController tester = new WebController();
		
		Vector<Movie> test = tester.getBestMovies();
		
		int size = test.size();
		
		assertEquals(test.elementAt(0).getYear() , "1962");
		assertEquals(test.elementAt(size-1).getYear() , "2017");
		assertEquals(test.elementAt(10).getYear() , "1972");

	}

	@Test
	public void testGetBestMovieByYear() throws IOException
	{
			WebController tester = new WebController();
		
			Movie test = tester.getBestMovieByYear("1961");

	        assertTrue(test.getYear() == "");
	        assertTrue(test.getID() == 0);
	        assertTrue(test.getTitle() == "");
	        
	        test = tester.getBestMovieByYear("1997");
	        
	        assertTrue(test.getYear().equals("1997"));
	        assertTrue(test.getID() == 36);
	        assertTrue(test.getTitle().equals("Titanic"));
	        
	        test = tester.getBestMovieByYear("3000");
	        
	        assertTrue(test.getYear() == "");
	        assertTrue(test.getID() == 0);
	        assertTrue(test.getTitle() == "");
	        
	        
	}

	@Test
	public void testGetBestMovieByYear2() throws IOException
	{
		WebController tester = new WebController();
		
		Movie test = tester.getBestMovieByYear2("1961");

        assertTrue(test.getYear() == "");
        assertTrue(test.getID() == 0);
        assertTrue(test.getTitle() == "");
        
        test = tester.getBestMovieByYear2("1997");
        
        assertTrue(test.getYear().equals("1997"));
        assertTrue(test.getID() == 36);
        assertTrue(test.getTitle().equals("Titanic"));
        
        test = tester.getBestMovieByYear2("3000");
        
        assertTrue(test.getYear() == "");
        assertTrue(test.getID() == 0);
        assertTrue(test.getTitle() == "");
	}

	@Test
	public void testGetBestMovieNomineesAll() throws IOException
	{
		WebController tester = new WebController();
		
		Vector<Movie> test = tester.getBestMovieNomineesAll();
		
		int size = test.size();
		
		assertEquals(test.elementAt(0).getYear() , "1962");
		assertEquals(test.elementAt(size-1).getYear() , "2017");
		assertEquals(test.elementAt(10).getYear() , "1964");
	}

	@Test
	public void testGetBestMovieNomineesByYear() throws IOException
	{
		WebController tester = new WebController();
		
		Vector<Movie> test = tester.getBestMovieNomineesByYear("1997");
        
        assertTrue(test.elementAt(0).getYear().equals("1997"));
        assertTrue(test.elementAt(0).getID() == 176);
        assertTrue(test.elementAt(0).getTitle().equals("As Good as It Gets"));

	}

}
