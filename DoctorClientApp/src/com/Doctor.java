package com;

import java.sql.*;



public class Doctor {
	
	//connection the code to the mysql db
	public Connection connect(){
		Connection con = null;
	 try
	 {
	       Class.forName("com.mysql.jdbc.Driver");
	       //Provide the correct details: DBServer/DBName, username, password
	       //extra code line added to the DB server because of an server time zone error.
	       con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
	       
	       //For testing
	       System.out.print("Successfully connected");
	 }
	 catch(Exception e){

	    e.printStackTrace();
	 }
	 return con;
	}
	
	
	//viewing the list of doctors
	
	public String readDoctor(){

		String output = "";
		try
		{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}


				// Prepare the html table to be displayed
				
				output = "<table border='1'><tr><th>Doctor Name</th><th>Specialization</th><th>Hospital</th><th>Contact</th><th>Email</th><th>Status</th><th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from doctors";
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String docID = Integer.toString(rs.getInt("docID"));
					String docName = rs.getString("docName");
					String docSpec = rs.getString("docSpec");
					String docHosp = rs.getString("docHosp");
					String docContact = Integer.toString(rs.getInt("docContact"));
					String docEmail = rs.getString("docEmail");
					String docStatus = rs.getString("docStat");
                 //   System.out.println(docID);
					// Add into the html table
					output += "<tr><td><input id='hiddocIDUpdate' name='hiddocIDUpdate' type='hidden' value='"+ docID + "'>" + docName + "</td>";
					output += "<td>" + docSpec + "</td>";
					output += "<td>" + docHosp + "</td>";
					output += "<td>" + docContact + "</td>";
					output += "<td>" + docEmail + "</td>";
					output += "<td>" + docStatus + "</td>";
					
					// buttons
					output += "<td><input name= 'btnUpdate' type= 'button' value= 'Update' class='btnUpdate btn btn-secondary'></td>"
							+ "<td><input name='btnRemove' type='button' value= 'Remove' class='btnRemove btn btn-danger' data-docid='" + docID + "'>" + "</td></tr>";
					
					
				}

				con.close();
				// Complete the html table
				output += "</table>";
		}
		catch (Exception e)
		{
				output = "Error while reading the values.";
				System.err.println(e.getMessage());
		}
	return output;
}
	
	//implementing the inserting method
public String insertDoctor(String doctorName, String specialization, String hospital, String contact, String email, String status){
	    
	    String output = "";
		
		try{
	         Connection con = connect();
	         if (con == null)
	         {
	        	 
	             return "Error while connecting to the database";
	             
	         }
	         
	        // create a prepared statement
	        String query = " insert into doctors(`docID`,`docName`,`docSpec`,`docHosp`,`docContact`,`docEmail`,`docStat`)" + " values (?, ?, ?, ?, ?, ?, ?)";
	        
	        PreparedStatement preparedStmt = con.prepareStatement(query);

	        System.out.println("Test");	
	        
	        // binding values
	        preparedStmt.setInt(1, 0);
	        preparedStmt.setString(2, doctorName);
	        preparedStmt.setString(3, specialization);
	        preparedStmt.setString(4, hospital);
	        preparedStmt.setInt(5, Integer.parseInt(contact));
	        preparedStmt.setString(6, email);
	        preparedStmt.setString(7, status);

	        //execute the statement
	        preparedStmt.execute();
	        con.close();
	        
	        String newDoctor = readDoctor();
	        output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
	        

		}catch(Exception e){
			
	        output = "{\"status\":\"error\", \"data\": \"Error while inserting the doctor.\"}";
	        System.err.println(e.getMessage());
		}

		return output;
		
	}


	//implementing the updating method
public String updateDoctor(String doctorID, String doctorName, String specialization, String hospital, String contact, String email, String status){
    
    String output = "";

    try{

           Connection con = connect();
           if (con == null){
           return "Error while connecting to the database for updating.";
           }
           
           // create a prepared statement

           String query = "UPDATE doctors SET docName=?,docSpec=?,docHosp=?,docContact=?,docEmail=?,docStat=? WHERE docID=?";
           PreparedStatement preparedStmt = con.prepareStatement(query);
           
           //binding values
           preparedStmt.setString(1, doctorName);
           preparedStmt.setString(2, specialization);
           preparedStmt.setString(3, hospital);
           preparedStmt.setInt(4, Integer.parseInt(contact));
           preparedStmt.setString(5, email);
           preparedStmt.setString(6, status);
           preparedStmt.setInt(7, Integer.parseInt(doctorID));

           // execute the statement
           preparedStmt.execute();
           con.close();

           String newDoctor = readDoctor();
	       output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
           


        }catch(Exception e){
        	output = "{\"status\":\"error\", \"data\":\"Error while updating the doctor.\"}";
			System.err.println(e.getMessage());
        }

        return output;

 }


	//implementing the deleting  method
public String deleteDoctor(String doctorID){

	String output = "";

	try{

		Connection con = connect();
		if (con == null){

         return "Error while connecting to the database for deleting.";
        }

        // create a prepared statement
        String query = "delete from doctors where docID=?";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        // binding values
        preparedStmt.setInt(1, Integer.parseInt(doctorID));
        // execute the statement
        preparedStmt.execute();
        con.close();

        String newDoctor = readDoctor();
	    output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";


	}catch (Exception e){

		output = "{\"status\":\"error\", \"data\":\"Error while deleting  the doctor.\"}";
		System.err.println(e.getMessage());
	}

	return output;
}

}
