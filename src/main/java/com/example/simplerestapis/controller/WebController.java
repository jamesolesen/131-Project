package com.example.simplerestapis.controller;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplerestapis.models.PostRequest;
import com.example.simplerestapis.models.PostResponse;
import com.example.simplerestapis.models.SampleResponse;

@RestController
public class WebController {

	@RequestMapping("/sample")
	public SampleResponse Sample(@RequestParam(value = "name",
	defaultValue = "Robot") String name) {
		SampleResponse response = new SampleResponse();
		response.setId(1);
		response.setMessage("Your name is "+name);
		return response;

	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public PostResponse Test(@RequestBody PostRequest inputPayload) {
		PostResponse response = new PostResponse();
		response.setId(inputPayload.getId()*100);
		response.setMessage("Hello " + inputPayload.getName());
		response.setExtra("Some text");
		return response;
	}

	
	@RequestMapping(value = "/james", produces = {"application/json"})
	public String someMethod(@RequestParam(value = "id") String parameter)
	{
		   Hashtable<String, String> numbers
		     = new Hashtable<String, String>();
		   numbers.put("one", "1");
		   numbers.put("two", "2");
		   numbers.put("three", "3");
		   return numbers.get(parameter);
		
	}
	
	@RequestMapping(value = "/movie/{id}", produces = {"application/json"})
	public String someMethod2(@PathVariable(value = "id") String parameter )
	{
		   Hashtable<String, String> numbers
		     = new Hashtable<String, String>();
		   numbers.put("one", "1");
		   numbers.put("two", "2");
		   numbers.put("three", "3");
		   return numbers.get(parameter);
		
		
	}
	
	@RequestMapping(value = "/movies/collectionTest", produces = {"application/json"})
	public Hashtable<String,String>someMethod3()
	{
		Hashtable<String, String> numbers
	     = new Hashtable<String, String>();
	   numbers.put("one", "1");
	   numbers.put("two", "2");
	   numbers.put("three", "3");
	   
	   return numbers;

	}
	
	@RequestMapping(value = "/movies/bestMovies" , produces = {"application/json"})
	public Vector<Movie> getBestMovies() throws FileNotFoundException,IOException,IndexOutOfBoundsException
	{
		Vector<Movie> collection = new Vector<Movie>();
		Vector<String> temp = new Vector<String>();
		
		String csvFile = "C://Users/james/Downloads/Oscar_Winner_data_csv.csv";  //enter filepath here
	    InputStream in = new FileInputStream(csvFile);
	    CSV csv = new CSV(true,',', in);
	    
	    temp = csv.getInfo();
	    
	    String hold;
	    int x = 1;
	    
	    for(int i = 0 ; i < temp.size(); i ++)
	    {
	    	hold = temp.get(i);
	    	
	    	if(hold.charAt(7) == 'B' && hold.charAt(12)=='P' && hold.charAt(21) == 'T')
	    	{
	    		collection.add(new Movie( hold.substring(27 , hold.length() - 1), hold.substring(1, 5) , x));
	    		x++;
	    		
	    	}
	    		
	    }

		return collection;
		
	}//end getBestMovies()
	
	@RequestMapping(value = "/movies/bestMovie", produces = {"application/json"})
	public Movie getBestMovieByYear(@RequestParam(value = "year") String parameter) throws FileNotFoundException,IOException
	{
		Vector<Movie> collection = new Vector<Movie>();
		Vector<String> temp = new Vector<String>();
		
		String csvFile = "C://Users/james/Downloads/Oscar_Winner_data_csv.csv";  //enter filepath here
	    InputStream in = new FileInputStream(csvFile);
	    CSV csv = new CSV(true,',', in);
	    
	    temp = csv.getInfo();
	    
	    String hold;
	    int x = 1;
	    
	    //load best movies into Vector
	    
	    for(int i = 0 ; i < temp.size(); i ++)
	    {
	    	hold = temp.get(i);
	    	
	    	if(hold.charAt(7) == 'B' && hold.charAt(12)=='P' && hold.charAt(21) == 'T')
	    	{
	    		collection.add(new Movie(hold.substring(27 , hold.length() - 1), hold.substring(1, 5) , x));
	    		x++;
	    		
	    	}//end if
	    		
	    }//end for
	    
	    boolean found = false;
	    int index = 0;
	    String data;
	    
	    //find result and return it
	    while(found==false && index < collection.size())
	    {
	    	data = collection.elementAt(index).getYear();
	    	
	    	if(data.equals(parameter))
	    		found = true;
	    	
	    	else
	    		index++;
	    }//end while
	      
	    if(found == true)
	    	return collection.elementAt(index);
	    	    	
	    else
	    	return new Movie();
	    
	}//end getBestMovieByYear
	
	@RequestMapping(value = "/movies/bestMovie/{year}", produces = {"application/json"})
	public Movie getBestMovieByYear2(@PathVariable(value = "year") String parameter ) throws FileNotFoundException,IOException
	{
		Vector<Movie> collection = new Vector<Movie>();
		Vector<String> temp = new Vector<String>();
		
		String csvFile = "C://Users/james/Downloads/Oscar_Winner_data_csv.csv";  //enter filepath here
	    InputStream in = new FileInputStream(csvFile);
	    CSV csv = new CSV(true,',', in);
	    
	    temp = csv.getInfo();
	    
	    String hold;
	    int x = 1;
	    
	    //load best movies into Vector
	    
	    for(int i = 0 ; i < temp.size(); i ++)
	    {
	    	hold = temp.get(i);
	    	
	    	if(hold.charAt(7) == 'B' && hold.charAt(12)=='P' && hold.charAt(21) == 'T')
	    	{
	    		collection.add(new Movie(hold.substring(27 , hold.length() - 1), hold.substring(1, 5) , x));
	    		x++;
	    		
	    	}//end if
	    		
	    }//end for
	    
	    boolean found = false;
	    int index = 0;
	    String data;
	    
	    //find result and return it
	    while(found==false && index < collection.size())
	    {
	    	data = collection.elementAt(index).getYear();
	    	
	    	if(data.equals(parameter))
	    		found = true;
	    	
	    	else
	    		index++;
	    }//end while
	      
	    if(found == true)
	    	return collection.elementAt(index);
	    	    	
	    else
	    	return new Movie();
		
		
	}
	
	
	
	
	
	class Movie{
		   private String title;
		   private String year;
		   private int id;

		   //Overload class constructor for input vars upon creation
		   Movie (String title, String year, int id){
		      this.title = title;
		      this.year = year;
		      this.id = id;
		   }
		   
		   Movie()
		   {
			   this.title = "";
			   this.year = "";
		   }
		   
		   //Getters & setters for manual var. retrieval/ input
		   public String getTitle(){
		      return this.title;
		   }
		   
		   public void setTitle(String title){
		      this.title = title;
		   }
		   
		   public String getYear(){
		      return this.year;
		   }
		   
		   public void setYear(String year){
		      this.year = year;     
		   }
		   
		   public void setID(int id)
		   {
			   this.id = id;
		   }
		   
		   public int getID()
		   {
			   return this.id;
		   }
		   
		} 
	
	
	
	
	class CSV
	{
	  static final private int NUMMARK = 10;
	  static final private char COMMA = ',';
	  static final private char DQUOTE = '"';
	  static final private char CRETURN = '\r';
	  static final private char LFEED = '\n';
	  static final private char SQUOTE = '\'';
	  static final private char COMMENT = '#';

	  /**
	   * Should we ignore multiple carriage-return/newline characters
	   * at the end of the record?
	   */
	  private boolean stripMultipleNewlines;

	  /**
	   * What should be used as the separator character?
	   */
	  private char separator;
	  private ArrayList<String> fields;
	  private boolean eofSeen;
	  private Reader in;

	  public Reader stripBom(InputStream in)
	    throws java.io.IOException,
	           java.io.UnsupportedEncodingException
	  {
	    PushbackInputStream pin = new PushbackInputStream(in, 3);
	    byte[] b = new byte[3];
	    int len = pin.read(b, 0, b.length);
	    if ( (b[0] & 0xFF) == 0xEF && len == 3 ) {
	      if ( (b[1] & 0xFF) == 0xBB && 
	           (b[2] & 0xFF) == 0xBF ) {
	        return new InputStreamReader(pin, "UTF-8");
	      } else {
	        pin.unread(b, 0, len);
	      }
	    }
	    else if ( len >= 2 ) {
	      if ( (b[0] & 0xFF) == 0xFE &&
	           (b[1] & 0xFF) == 0xFF ) {
	        return new InputStreamReader(pin, "UTF-16BE");
	      } else if ( (b[0] & 0xFF) == 0xFF &&
	                  (b[1] & 0xFF) == 0xFE ) {
	        return new InputStreamReader(pin, "UTF-16LE");
	      } else {
	        pin.unread(b, 0, len);
	      }
	    } else if ( len > 0 ) {
	      pin.unread(b, 0, len);
	    }
	    return new InputStreamReader(pin, "UTF-8");
	  }

	  public CSV(boolean stripMultipleNewlines,
	             char separator,
	             Reader input)
	  {
	    this.stripMultipleNewlines = stripMultipleNewlines;
	    this.separator = separator;
	    this.fields = new ArrayList<String>();
	    this.eofSeen = false;
	    this.in = new BufferedReader(input);
	  }

	  public CSV(boolean stripMultipleNewlines,
	             char separator,
	             InputStream input)
	    throws java.io.IOException,
	           java.io.UnsupportedEncodingException
	  {
	    this.stripMultipleNewlines = stripMultipleNewlines;
	    this.separator = separator;
	    this.fields = new ArrayList<String>();
	    this.eofSeen = false;
	    this.in = new BufferedReader(stripBom(input));
	  }

	  public boolean hasNext() throws java.io.IOException
	  {
	    if ( eofSeen ) return false;
	    fields.clear();
	    eofSeen = split( in, fields );
	    if ( eofSeen ) return ! fields.isEmpty();
	    else return true;
	  }

	  public List<String> next()
	  {
	    return fields;
	  }

	  // Returns true if EOF seen.
	  private boolean discardLinefeed(Reader in,
	                                         boolean stripMultiple)
	    throws java.io.IOException
	  {
	    if ( stripMultiple ) {
	      in.mark(NUMMARK);
	      int value = in.read();
	      while ( value != -1 ) {
	        char c = (char)value;
	        if ( c != CRETURN && c != LFEED ) {
	          in.reset();
	          return false;
	        } else {
	          in.mark(NUMMARK);
	          value = in.read();
	        }
	      }
	      return true;
	    } else {
	      in.mark(NUMMARK);
	      int value = in.read();
	      if ( value == -1 ) return true;
	      else if ( (char)value != LFEED ) in.reset();
	      return false;
	    }
	  }

	  private boolean skipComment(Reader in)
	    throws java.io.IOException
	  {
	    /* Discard line. */
	    int value;
	    while ( (value = in.read()) != -1 ) {
	      char c = (char)value;
	      if ( c == CRETURN )
	        return discardLinefeed( in, stripMultipleNewlines );
	    }
	    return true;
	  }

	  // Returns true when EOF has been seen.
	  private boolean split(Reader in,ArrayList<String> fields)
	    throws java.io.IOException
	  {
	    StringBuilder sbuf = new StringBuilder();
	    int value;
	    while ( (value = in.read()) != -1 ) {
	      char c = (char)value;
	      switch(c) {
	      case CRETURN:
	        if ( sbuf.length() > 0 ) {
	          fields.add( sbuf.toString() );
	          sbuf.delete( 0, sbuf.length() );
	        }
	        return discardLinefeed( in, stripMultipleNewlines );

	      case LFEED:
	        if ( sbuf.length() > 0 ) {
	          fields.add( sbuf.toString() );
	          sbuf.delete( 0, sbuf.length() );
	        }
	        if ( stripMultipleNewlines )
	          return discardLinefeed( in, stripMultipleNewlines );
	        else return false;

	      case DQUOTE:
	        {
	          // Processing double-quoted string ..
	          while ( (value = in.read()) != -1 ) {
	            c = (char)value;
	            if ( c == DQUOTE ) {
	              // Saw another double-quote. Check if
	              // another char can be read.
	              in.mark(NUMMARK);
	              if ( (value = in.read()) == -1 ) {
	                // Nope, found EOF; means End of
	                // field, End of record and End of
	                // File
	                if ( sbuf.length() > 0 ) {
	                  fields.add( sbuf.toString() );
	                  sbuf.delete( 0, sbuf.length() );
	                }
	                return true;
	              } else if ( (c = (char)value) == DQUOTE ) {
	                // Found a second double-quote
	                // character. Means the double-quote
	                // is included.
	                sbuf.append( DQUOTE );
	              } else if ( c == CRETURN ) {
	                // Found End of line. Means End of
	                // field, and End of record.
	                if ( sbuf.length() > 0 ) {
	                  fields.add( sbuf.toString() );
	                  sbuf.delete( 0, sbuf.length() );
	                }
	                // Read and discard a line-feed if we
	                // can indeed do so.
	                return discardLinefeed( in,
	                                        stripMultipleNewlines );
	              } else if ( c == LFEED ) {
	                // Found end of line. Means End of
	                // field, and End of record.
	                if ( sbuf.length() > 0 ) {
	                  fields.add( sbuf.toString() );
	                  sbuf.delete( 0, sbuf.length() );
	                }
	                // No need to check further. At this
	                // point, we have not yet hit EOF, so
	                // we return false.
	                if ( stripMultipleNewlines )
	                  return discardLinefeed( in, stripMultipleNewlines );
	                else return false; 
	              } else {
	                // Not one of EOF, double-quote,
	                // newline or line-feed. Means end of
	                // double-quote processing. Does NOT
	                // mean end-of-field or end-of-record.
	                // System.err.println("EOR on '" + c +
	                // "'");
	                in.reset();
	                break;
	              }
	            } else {
	              // Not a double-quote, so no special meaning.
	              sbuf.append( c );
	            }
	          }
	          // Hit EOF, and did not see the terminating double-quote.
	          if ( value == -1 ) {
	            // We ignore this error, and just add whatever
	            // left as the next field.
	            if ( sbuf.length() > 0 ) {
	              fields.add( sbuf.toString() );
	              sbuf.delete( 0, sbuf.length() );
	            }
	            return true;
	          }
	        }
	        break;

	      default:
	        if ( c == separator ) {
	          fields.add( sbuf.toString() );
	          sbuf.delete(0, sbuf.length());
	        } else {
	          /* A comment line is a line starting with '#' with
	           * optional whitespace at the start. */
	          if ( c == COMMENT && fields.isEmpty() &&
	               sbuf.toString().trim().isEmpty() ) {
	            boolean eof = skipComment(in);
	            if ( eof ) return eof;
	            else sbuf.delete(0, sbuf.length());
	            /* Continue with next line if not eof. */
	          } else sbuf.append(c);
	        }
	      }
	    }
	    if ( sbuf.length() > 0 ) {
	      fields.add( sbuf.toString() );
	      sbuf.delete( 0, sbuf.length() );
	    }
	    return true;
	  }
	  
	  public Vector<String> getInfo() throws FileNotFoundException,IOException
	  {
	     String csvFile = "C://Users/james/Downloads/Oscar_Winner_data_csv.csv";  //enter filepath here
	     InputStream in = new FileInputStream(csvFile);
	     CSV csv = new CSV(true,',', in);
	     StringBuilder sb = new StringBuilder();
	     StringBuilder sbTemp = new StringBuilder();
	     
	     Vector<String> vec = new Vector();
	     
	     sb.append(csv.next());
	     
	     while (csv.hasNext())
	     {
	        sbTemp.append(csv.next());
	        sb.replace(0,sb.length(),sbTemp.toString());
	        sbTemp.setLength(0);
	        
	        
	        /*if(sb.charAt(7) == 'B' && sb.charAt(12)=='P' && sb.charAt(21) == 'T')
	        	System.out.println("In string: " + sb);
	        */
	        
	        vec.add(sb.toString());
	        
	     }
	     
	     return vec;
	     
	     
	  }
	}
	
	
	
	
}
