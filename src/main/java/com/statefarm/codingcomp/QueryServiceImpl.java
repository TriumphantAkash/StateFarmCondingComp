package com.statefarm.codingcomp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.statefarm.codingcomp.model.Email;
import com.statefarm.codingcomp.model.User;
import com.statefarm.codingcomp.reader.Reader;

public class QueryServiceImpl implements QueryService {

    Reader reader = new Reader();

    @Override
    public List<User> usersByDomain( String domain ) throws Exception {
    	List<User> userList  = new ArrayList<User>();
    	String []str1;
    	String []str2; 
    	String []str3;
    	String []str4;
    	
    	
        // TODO Auto-generated method stub
    	str1 = reader.read(1, "user.txt");	//file 1
    	str2 = reader.read(2, "user.txt");  //file 2
    	str3 = reader.read(3, "user.txt");  //file 3
    	str4 = reader.read(4, "user.txt");   	//file 4
        
    	for(int i = 0; i< str1.length; i++) {
    		if(str1[i].contains(domain)) {
    			String []userInfo = str1[i].split(",");
    			User user = new User(userInfo[1], userInfo[2]);
    			
    			userList.add(user);
    		}
    	}    	
    	for(int i = 0; i< str2.length; i++) {
    		if(str2[i].contains(domain)) {
    			String []userInfo = str2[i].split(",");
    			User user = new User(userInfo[1], userInfo[2]);
    			
    			userList.add(user);
    		}
    	}  
    	for(int i = 0; i< str3.length; i++) {
    		if(str3[i].contains(domain)) {
    			String []userInfo = str3[i].split(",");
    			User user = new User(userInfo[1], userInfo[2]);
    			
    			userList.add(user);
    		}
    	}  
    	for(int i = 0; i< str4.length; i++) {
    		if(str4[i].contains(domain)) {
    			String []userInfo = str4[i].split(",");
    			User user = new User(userInfo[1], userInfo[2]);
    			
    			userList.add(user);
    		}
    	}  
    	return userList; 
    }

    @Override
    public List<Email> emailsInDateRange( Date start, Date end ) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, List<Email>> emailsByDay() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int emailsOnDay( Date date ) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Map<User, List<Email>> emailsFromOurUsers() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<User, List<Email>> emailsToOurUsers() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Email> emailsToUserFromUser() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<String> emailAddressesByDegrees( String email, int degrees ) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int degreesBetween( String emailAddressOne, String emailAddressTwo ) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String mostConnected() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String mostActiveSender() throws Exception {
        // TODO Auto-generated method stub    	
    	Map<String, Integer> countUser = new HashMap<String, Integer>();
    	List<String> allSenders = new ArrayList<>();
    	int count = 0;
    	
    	String []str1;
    	String []str2; 
    	String []str3;
    	String []str4;
    	
    	
        // TODO Auto-generated method stub
    	str1 = reader.read(1, "mail.txt");	//file 1
    	str2 = reader.read(2, "mail.txt");  //file 2
    	str3 = reader.read(3, "mail.txt");  //file 3
    	str4 = reader.read(4, "mail.txt");   	//file 4
        
    	for(int i = 0; i< str1.length; i++) {
    			String []mailInfo = str1[i].split(",");
    			allSenders.add(mailInfo[1]);
    	}
    	for(int i = 0; i< str2.length; i++) {
			String []mailInfo = str2[i].split(",");
			allSenders.add(mailInfo[1]);
    	}
    	for(int i = 0; i< str3.length; i++) {
			String []mailInfo = str3[i].split(",");
			allSenders.add(mailInfo[1]);
    	}
    	for(int i = 0; i< str4.length; i++) {
			String []mailInfo = str4[i].split(",");
			allSenders.add(mailInfo[1]);
    	}
    	
    	for (String temp : allSenders) {
    		countUser.put(temp, Collections.frequency(allSenders, temp));
    	}
    	
    	int maxValueInMap=(Collections.max(countUser.values()));  // This will return max value in the Hashmap
        for (Entry<String, Integer> entry : countUser.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                return entry.getKey();    //return the maximum value
            }
        }
        return null;
    }

    @Override
    public String mostActiveReceiver() throws Exception {
        // TODO Auto-generated method stub    	
    	Map<String, Integer> countUser = new HashMap<String, Integer>();
    	List<String> allReceiver = new ArrayList<>();
    	int count = 0;
    	
    	String []str1;
    	String []str2; 
    	String []str3;
    	String []str4;
    	
    	
        // TODO Auto-generated method stub
    	str1 = reader.read(1, "mail.txt");	//file 1
    	str2 = reader.read(2, "mail.txt");  //file 2
    	str3 = reader.read(3, "mail.txt");  //file 3
    	str4 = reader.read(4, "mail.txt");   	//file 4
        
    	for(int i = 0; i< str1.length; i++) {
    			String []mailInfo = str1[i].split(",");
    			allReceiver.add(mailInfo[0]);
    	}
    	for(int i = 0; i< str2.length; i++) {
			String []mailInfo = str2[i].split(",");
			allReceiver.add(mailInfo[0]);
    	}
    	for(int i = 0; i< str3.length; i++) {
			String []mailInfo = str3[i].split(",");
			allReceiver.add(mailInfo[0]);
    	}
    	for(int i = 0; i< str4.length; i++) {
			String []mailInfo = str4[i].split(",");
			allReceiver.add(mailInfo[0]);
    	}
    	
    	for (String temp : allReceiver) {
    		countUser.put(temp, Collections.frequency(allReceiver, temp));
    	}
    	
    	int maxValueInMap=(Collections.max(countUser.values()));  // This will return max value in the Hashmap
        for (Entry<String, Integer> entry : countUser.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                return entry.getKey();    //return the maximum value
            }
        }
        return null;
    }

    @Override
    public String mostSingularSender() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String mostSelfEmails() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int mostPopularHour() throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int mostPopularHour( String email ) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int leastPopularHour() throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int leastPopularHour( String email ) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String mostPopularDate() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String mostPopularDate( String email ) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String leastPopularDate() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String leastPopularDate( String email ) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
