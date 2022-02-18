/*
 * @author Aaron Hong
 * Created for a coding interview with MFGx
 * 
*/

import java.util.ArrayList;
public class SoftwareEngiEx {
	//Variables
	private String message;
	private String date;
	private int views;
	private String author;
	/*Constructor
	 * @param message;
	 * @param date;
	 * @param views;
	 * @param author;
	 */
	SoftwareEngiEx(String message, String date, int views, String author){//takes 4 para
		setMessage(message);
		setDate(date);
		setViews(views);
		setAuthor(author);
	}
	//Getter and Setter Methods
	public String getMessage() {
		return message;
	}
	/*
	 * @param message;
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	/*
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	public int getViews() {
		return views;
	}
	/*
	 * @param views
	 */
	public void setViews(int views) {
		this.views = views;
	}
	public String getAuthor() {
		return author;
	}
	/*
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	//Functions
	String formatDate(String date) {//formats a date string in MM/DD/YYYY
		String finalDate;//return value for new formatted date
		ArrayList <Character> dateNew  = new ArrayList<Character>();//character ArrayList used to manipulate date
		char arr[] = getDate().toCharArray();//regular array used to temporarily store unedited date
		for(int i = 0; i < arr.length - 1; i++) {//truncates date to only YYYY-MM-DATE
			if(arr[i] == 'T') {//Stops adding characters to ArrayList when it reaches the character 'T'
				break;
			}
			dateNew.add(arr[i]);
		}
		//char indices 0-3 are YYYY
		String year = dateNew.get(0).toString() + dateNew.get(1).toString() + dateNew.get(2).toString() + dateNew.get(3).toString(); 
		//char indices 5 and 6 are MM
		String month = dateNew.get(5).toString() + dateNew.get(6).toString();
		//char indices 8 and 9 are DD
		String day = dateNew.get(8).toString() + dateNew.get(9).toString();
		finalDate = month + "/" + day + "/" + year;
		return finalDate;
	}
	
	public void printChirp() {//Print
		String chirp = getMessage() + " " + formatDate(getDate()) + " " + getViews() + " " + getAuthor();//Initial untruncated chirp
		char checkChirp[];//array used to check chirp length
		char checkDate[] = formatDate(getDate()).toCharArray();//used to count number of characters of date
		char checkViews[] = String.valueOf(getViews()).toCharArray();//used to count number of characters of views
		char checkAuthor[] = getAuthor().toCharArray();//used to count number of characters of author
		ArrayList<Character> truncatedChirp = new ArrayList<Character>();//used if chirp needs to be truncated
		int limit = 140;//character limit for formatted output
		int miscLength = checkDate.length + checkViews.length + checkAuthor.length + 3; //length of everything after message + 3 empty space chars
		
		if(views > 100000) {
			chirp = chirp + " 🔥";//append string with an empty character space and fire emoji
			miscLength = checkDate.length + checkViews.length + checkAuthor.length + 6; //length of everything after message + 6 for empty space chars and fire emoji
		}
		
		checkChirp = chirp.toCharArray();
		if(checkChirp.length > limit) {//check if chirp's length is greater than 140
			for(int i = 0; i < checkChirp.length; i++) {
				truncatedChirp.add(checkChirp[i]);
			}
			int temp = miscLength;//value used to calculate position to truncate
			int chirpSize = truncatedChirp.size();//gets total size of chirp message before truncation
			
			while(truncatedChirp.size() > limit) {//logic to truncate message of chirp
				truncatedChirp.remove(chirpSize - temp);
				temp++;
			}
			//replaces the last 4 characters of the message portion of the chirp with "... "
			truncatedChirp.set(137-miscLength, '.');
			truncatedChirp.set(138-miscLength, '.');
			truncatedChirp.set(139-miscLength, '.');
			truncatedChirp.set(140-miscLength, ' ');
			StringBuilder sb = new StringBuilder(truncatedChirp.size());//string builder to transfer arrayList of characters to string
			for(char c : truncatedChirp) {
				sb.append(c);
			}
			
			chirp =	sb.toString();//Final formatted message assigned to chirp

		}
		System.out.println(chirp);
	}
	
	public static void main(String[] args) {
		//Test Case 1
		SoftwareEngiEx s = new SoftwareEngiEx("Hello World!", "2021-06-28T07:00:00.000Z", 99999, "Jack Daniels");
		s.printChirp();
		//Test Case 2
		String testcase_MSG2 = "This is a maximum length chirp because I like writing long chirps. Why? I'm not really sure. Maybe I'm just a rebel.. or maybe it's a phase?";
		String testcase_DATE2 = "2021-04-22T00:00:00.000Z";
		int testcase_VIEWS2 = 100;
		String testcase_AUTHOR2 = "John Smith";
		s.setMessage(testcase_MSG2);
		s.setAuthor(testcase_AUTHOR2);
		s.setDate(testcase_DATE2);
		s.setViews(testcase_VIEWS2);
		s.printChirp();
		//Test Case 3
		String testcase_MSG3 = "This chirp probably won't get a lot of attention.";
		String testcase_DATE3 = "2021-03-04T04:00:00.000Z";
		int testcase_VIEWS3 = 251236;
		String testcase_AUTHOR3 = "Jane Doe";
		s.setMessage(testcase_MSG3);
		s.setAuthor(testcase_AUTHOR3);
		s.setDate(testcase_DATE3);
		s.setViews(testcase_VIEWS3);
		s.printChirp();
		//Test Case 4
		String testcase_MSG4 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam consequat quis turpis non consectetur. Sed accumsan dui rhoncus, cursus quis.";
		String testcase_DATE4 = "2021-01-01T00:00:00.000Z";
		int testcase_VIEWS4 = 1000001;
		String testcase_AUTHOR4 = "Lorem Ipsum";
		s.setMessage(testcase_MSG4);
		s.setAuthor(testcase_AUTHOR4);
		s.setDate(testcase_DATE4);
		s.setViews(testcase_VIEWS4);
		s.printChirp();
	}
}

