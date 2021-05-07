
import java.sql.*;
import java.util.*;

public class ConnectDB {

	public static void main(String[] args) {
		Connection connect=null;

		try {
			Class.forName("org.postgresql.Driver");
			
			String username;
			String password;
			String ssn;
			String dSSN;
			Scanner in = new Scanner(System.in);

			System.out.println("Enter Username:");
			username=in.next();
			System.out.println("Enter password:");
			password=in.next();
			connect=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",username,password);

			Statement stmt = connect.createStatement();
			boolean run=true;
			double option, option1,option2,amount;
			String value;


			while(run) {
				System.out.println("What would you like to do? Enter the number next to the desired option.");
				System.out.println("0. Logout");
				System.out.println("1. Run Reports");
				System.out.println("2. View profile");
				System.out.println("3. Update Tables");
				System.out.println("4. Insert Employee");
				System.out.println("5. Delete Employee");
				System.out.println("6. Add User");
				System.out.println("7. Edit Bonus");
				System.out.println("8. Add Benefits");
				System.out.println("9. Add Dependent");
				System.out.println("10. Add Dependent Benefits");
				System.out.println("11. Add Employer Expenses");
				System.out.println("12. Add Employee expenses");
				option=in.nextInt();

				if(option==0) {
					System.out.println("Logged off");
					run=false;
				}
				if(option==1) {
					System.out.println("1.Bi-weekly check");
					System.out.println("2. Employee W2");
					System.out.println("3.Employer Expenses");
					option1=in.nextInt();

					if(option1==1) {
						System.out.println("Please enter SSN number of employee");
						ssn=in.next();
						String sql= "select * from paycheck where employeeSSN=" +ssn;
						ResultSet rset= stmt.executeQuery(sql);
						ResultSetMetaData rsmd = rset.getMetaData();

						int columnsNumber = rsmd.getColumnCount();
						while (rset.next()) {
							for (int i = 1; i <= columnsNumber; i++) {
								if (i > 1) System.out.print(",  ");
								String columnValue = rset.getString(i);
								System.out.print(columnValue+ " " + rsmd.getColumnName(i));
							}
							System.out.println("");
						}
						System.out.println("Done. Now Loading menu...");
						Thread.sleep(3000);
						
					}
					if(option1==2) {
						System.out.println("Please enter SSN number of employee");
						ssn=in.next();
						String sql= "select * from w2 where employeeSSN=" +ssn;
						ResultSet rset= stmt.executeQuery(sql);
						ResultSetMetaData rsmd = rset.getMetaData();

						int columnsNumber = rsmd.getColumnCount();
						while (rset.next()) {
							for (int i = 1; i <= columnsNumber; i++) {
								if (i > 1) System.out.print(",  ");
								String columnValue = rset.getString(i);
								System.out.print(columnValue+ " " + rsmd.getColumnName(i));
							}
							System.out.println("");
						}
						System.out.println("Done. Now Loading menu...");
						Thread.sleep(3000);
					}
					if(option1==3) {
						System.out.println("Please enter SSN number of employee");
						ssn=in.next();
						String sql= "select * from CompanyExpenseReport where employeeSSN=" +ssn;
						ResultSet rset= stmt.executeQuery(sql);
						ResultSetMetaData rsmd = rset.getMetaData();

						int columnsNumber = rsmd.getColumnCount();
						while (rset.next()) {
							for (int i = 1; i <= columnsNumber; i++) {
								if (i > 1) System.out.print(",  ");
								String columnValue = rset.getString(i);
								System.out.print(columnValue+ " " + rsmd.getColumnName(i));
							}
							System.out.println("");
						}
						System.out.println("Done. Now Loading menu...");
						Thread.sleep(3000);
					}
				}
				if(option==2) {
					System.out.println("Please enter your SSN or the SSN of the employee you want to see");
					ssn=in.next();
					String sql= "select * from profile where employeeSSN= " + ssn;

					ResultSet rset= stmt.executeQuery(sql);

					ResultSetMetaData rsmd = rset.getMetaData();

					int columnsNumber = rsmd.getColumnCount();

					while (rset.next()) {
						for (int i = 1; i <= columnsNumber; i++) {
							if (i > 1) System.out.print(",  ");
							String columnValue = rset.getString(i);
							System.out.print(columnValue+ " " + rsmd.getColumnName(i));
						}
						System.out.println("");
					}
					System.out.println("Done. Now Loading menu...");
					Thread.sleep(3000);
				}
				if(option==3) {
					System.out.println("Please enter SSN of which employee you wish to edit:");
					ssn=in.next();
					System.out.println("What table do you want to edit?");
					System.out.println("1. Benefits");
					System.out.println("2. Employee");
					System.out.println("3. Dependent");
					System.out.println("4. Dependent Benefits");
					System.out.println("5. Employee Expenses");
					System.out.println("6. Employer Expenses");

					option1=in.nextInt();
					if(option1==1) {
						System.out.println("What attribute do you want to edit?");
						System.out.println("1. Premium Individual");
						System.out.println("2. Premium Family");
						System.out.println("3. 401k");
						System.out.println("4. Health Plan");
						System.out.println("5. Attorney Plan");
						option2=in.nextInt();
						if(option2==1) {
							System.out.println("What do you want to set premium individual to?");
							amount=in.nextInt();
							String sql= "update benefits set premiumindividual= " + amount + " where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==2) {
							System.out.println("What do you want to set premium family to?");
							amount=in.nextInt();
							String sql= "update benefits set premiumfamily= " + amount + " where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);

						}
						if(option2==3) {
							System.out.println("What do you want to set 401k to?");
							amount=in.nextInt();
							String sql= "update benefits set fourohonekayok= " + amount + " where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);

						}
						if(option2==4) {
							System.out.println("Whats the name of health plan? Type 'GoodOne' or 'BadOne'");
							value=in.next();
							String sql= "update benefits set healthplan= '" + value + "' where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);

						}
						if(option2==5) {
							System.out.println("Whats the name of attorney plan? Type 'GoodOne' or 'BadOne'");
							value=in.next();
							String sql= "update benefits set attorneyplan= '" + value + "' where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
					}
					if(option1==2) {
						System.out.println("What attribute do you want to edit?");
						System.out.println("1. First Name");
						System.out.println("2. Last Name");
						System.out.println("3. Job Title");
						System.out.println("4. Salary Type");
						System.out.println("5. State Name");
						option2=in.nextInt();
						if(option2==1) {
							System.out.println("Whats the first name?");
							value=in.next();
							String sql= "update employee set firstname= '" + value + "' where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==2) {
							System.out.println("Whats the last name?");
							value=in.next();
							String sql= "update employee set lastname= '" + value + "' where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==3) {
							System.out.println("Whats the new job title?");
							value=in.next();
							String sql= "update employee set jobtitle= '" + value + "' where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==4) {
							System.out.println("Whats the salary type?");
							value=in.next();
							String sql= "update employee set salarytype= '" + value + "' where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==6) {
							System.out.println("Whats the State?");
							value=in.next();
							String sql= "update employee set statename= '" + value + "' where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
					}
					if(option1==3) {
						System.out.println("Which dependent do you want to edit?");
						dSSN=in.next();
						System.out.println("What attribute do you want to edit?");
						System.out.println("1. First Name");
						System.out.println("2. Last Name");
						System.out.println("3. Realtionship");

						option2=in.nextInt();
						if(option2==1) {
							System.out.println("Whats the first name?");
							value=in.next();
							String sql= "update dependent set firstname= '" + value + "' where employeeSSN= " + ssn + " and dependentssn="+ dSSN;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==2) {
							System.out.println("Whats the last name?");
							value=in.next();
							String sql= "update dependent set lastname= '" + value + "' where employeeSSN= " + ssn+ " and dependentssn="+ dSSN;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==3) {
							System.out.println("Whats the new relation?");
							value=in.next();
							String sql= "update dependent set relation= '" + value + "' where employeeSSN= " + ssn + " and dependentssn="+ dSSN;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}

					}
					if(option1==4) {
						System.out.println("Which dependent do you want to edit?");
						dSSN=in.next();
						System.out.println("Which dependent benefits do you want to edit?");
						System.out.println("1. Dental");
						System.out.println("2. Vision");
						System.out.println("3. Life");
						option2=in.nextInt();
						if(option2==1) {
							System.out.println("What dental insurance?");
							value=in.next();
							String sql= "update dependentbenefits set dentalinsurance= '" + value + "' where employeeSSN= " + ssn + " and dependentssn="+ dSSN;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==2) {
							System.out.println("What vision insurance?");
							value=in.next();
							String sql= "update dependentbenefits set visioninsurance= '" + value + "' where employeeSSN= " + ssn+ " and dependentssn="+ dSSN;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==3) {
							System.out.println("What life insurance?");
							value=in.next();
							String sql= "update dependentbenefits set lifeinsurance= '" + value + "' where employeeSSN= " + ssn + " and dependentssn="+ dSSN;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}

					}
					if(option1==5) {
						System.out.println("Which attributes do you want to edit?");
						System.out.println("1. Federal Tax Rate");
						System.out.println("2. Medicare Tax");
						System.out.println("3. Social Security Contribution");
						System.out.println("4. 401k contribution");
						System.out.println("5. Health Plan Contribution");
						System.out.println("6. Attorney Plan Contribution");
						option2=in.nextInt();

						if(option2==1) {
							System.out.println("Whats updated Federal tax rate?");
							amount=in.nextDouble();
							String sql= "update employeeexpenses set federaltaxrate= " + amount + "where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==2) {
							System.out.println("Whats Medicare Tax?");
							amount=in.nextDouble();
							String sql= "update employeeexpenses set medicaretax= " + amount + " where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==3) {
							System.out.println("Whats the employees new Social Security contribution?");
							amount=in.nextDouble();
							String sql= "update employeeexpenses set socialsecurityemployee= " + amount + " where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==4) {
							System.out.println("Whats the employees new 401k contribution?");
							amount=in.nextDouble();
							String sql= "update employeeexpenses set fourohonekayemployee= " + amount + " where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==5) {
							System.out.println("Whats the employees new health plan contribution?");
							amount=in.nextDouble();
							String sql= "update employeeexpenses set healthplanemployee= " + amount + " where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==6) {
							System.out.println("Whats the employees new attorney plan contribution?");
							amount=in.nextDouble();
							String sql= "update employeeexpenses set attorneyplan= " + amount + " where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
					}
					if(option1==6) {
						System.out.println("Which attributes do you want to edit?");
						System.out.println("1. Paycheck");
						System.out.println("2. Social Security Contribution");
						System.out.println("3. 401k contribution");
						System.out.println("4. Health Plan Contribution");
						System.out.println("5. Attorney Plan Contribution");
						option2=in.nextInt();

						if(option2==1) {
							System.out.println("Whats the employees new Paycheck amount?");
							amount=in.nextDouble();
							String sql= "update employerexpenses set paycheck= " + amount + "where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==2) {
							System.out.println("Whats the employers new Social Security contribution?");
							amount=in.nextDouble();
							String sql= "update employerexpenses set socialsecurityemployer= " + amount + " where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==3) {
							System.out.println("Whats the employers new 401k contribution?");
							amount=in.nextDouble();
							String sql= "update employerexpenses set fourohonekayemployer= " + amount + " where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==4) {
							System.out.println("Whats the employers new health plan contribution?");
							amount=in.nextDouble();
							String sql= "update employerexpenses set healthplanemployee= " + amount + " where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
						if(option2==5) {
							System.out.println("Whats the employers new attorney plan contribution?");
							amount=in.nextDouble();
							String sql= "update employerexpenses set attorneyplanemployee= " + amount + " where employeeSSN= " + ssn;
							stmt.executeUpdate(sql);
							System.out.println("updating...");
							Thread.sleep(3000);
							System.out.println("Done");
							Thread.sleep(2000);
						}
					}
				}
				if(option==4) {
					String fname,lname,jtitle,paytype,state;
					System.out.println("Please enter SSN of which employee you wish to add:");
					ssn=in.next();
					System.out.println("Enter Employee First Name:");
					fname = in.next();
					System.out.println("Enter Employee Last Name:");
					lname = in.next();
					System.out.println("Enter Employee Job Title:");
					jtitle = in.next();
					System.out.println("Enter Employee Pay Type:");
					paytype = in.next();
					System.out.println("Enter Employee State:");
					state = in.next();
					String sql = "INSERT INTO employee values(" + ssn + ",'" + fname + "','" + lname + "','" + jtitle + "', '" + paytype + "', '" + state + "')";
					stmt.executeUpdate(sql);
					System.out.println("inserting...");
					Thread.sleep(3000);
					System.out.println("Done");
					Thread.sleep(2000);
				}
				if(option==5) {
					System.out.println("Please enter SSN of which employee you wish to delete:");
					ssn=in.next();
					String sql = "delete from employee where employeessn=" + ssn ;
					stmt.executeUpdate(sql);
					System.out.println("deleting...");
					Thread.sleep(3000);
					System.out.println("Done");
					Thread.sleep(2000);

				}
				if(option==6) {
					System.out.println("Are you adding an 1.employee, 2.manager, or 3.admin? Enter 1, 2 or 3:");
					option1=in.nextInt();
					if(option1==1) {
						String uname,pname;
						System.out.println("Enter new Username");
						uname=in.next();
						System.out.println("Enter new Password");
						pname=in.next();
						String sql= "CREATE user " + uname + " with password '" + pname+ "'";
						stmt.executeUpdate(sql);
						String sql1="grant employee to " + uname;
						stmt.executeUpdate(sql1);
						System.out.println("granting...");
						Thread.sleep(3000);

					}
					if(option1==2) {
						String uname,pname;
						System.out.println("Enter new Username");
						uname=in.next();
						System.out.println("Enter new Password");
						pname=in.next();
						String sql= "CREATE user " + uname + " with password '" + pname+ "'";
						stmt.executeUpdate(sql);
						String sql1="grant manager to " + uname;
						stmt.executeUpdate(sql1);
						System.out.println("granting...");
						Thread.sleep(3000);

					}
					if(option1==3) {
						String uname,pname;
						System.out.println("Enter new Username");
						uname=in.next();
						System.out.println("Enter new Password");
						pname=in.next();
						String sql= "CREATE user " + uname + " with password '" + pname+ "'";
						stmt.executeUpdate(sql);
						String sql1="grant admin to " + uname;
						stmt.executeUpdate(sql1);
						System.out.println("granting...");
						Thread.sleep(3000);

					}
				}
				if(option==7) {
					System.out.println("Enter the SSN of the employees whos bonus you wish to change:");
					ssn=in.next();
					System.out.println("Enter the new performance/bonus amount:");
					amount=in.nextInt();
					String sql= "update employerexpenses set bonus= " + amount + " where employeeSSN= " + ssn;
					stmt.executeUpdate(sql);
					System.out.println("updating...");
					Thread.sleep(3000);

				}
				if(option==8) {
					String planID,premI,premf,fo1k,healthplan,attorneyplan;
					System.out.println("Enter the SSN of new employee:");
					ssn=in.next();
					System.out.println("Enter the new planID:");
					planID=in.next();
					System.out.println("Enter premium for individual:");
					premI=in.next();
					System.out.println("Enter premium for family:");
					premf=in.next();
					System.out.println("Enter 401k for family:");
					fo1k=in.next();
					System.out.println("Enter the health plan. Type GoodOne or BadOne");
					healthplan=in.next();
					System.out.println("Enter the attorney plan.GoodOne or BadOne");
					attorneyplan=in.next();
					String sql= "insert into benefits values (" + ssn + ",'" + planID + "'," + premI +"," + premf +"," + fo1k + ",'" + healthplan + "','"+ attorneyplan + "')";
					stmt.executeUpdate(sql);
					System.out.println("adding...");
					Thread.sleep(3000);
				}
				if(option==9) {
					String fname,lname,relation;
					System.out.println("Enter the SSN of employee:");
					ssn=in.next();
					System.out.println("Enter the SSN of new dependent:");
					dSSN=in.next();
					System.out.println("Enter the dependent first name:");
					fname=in.next();
					System.out.println("Enter the dependent last name");
					lname=in.next();
					System.out.println("Enter the relation to employee");
					relation=in.next();
					String sql= "insert into dependent values (" + ssn + "," + dSSN + ",'" + fname + "','" + lname +"','" + relation + "')";
					stmt.executeUpdate(sql);
					System.out.println("adding...");
					Thread.sleep(3000);
				}
				if(option==10) {
					String planID,dental,vision,life;
					System.out.println("Enter the SSN of employee:");
					ssn=in.next();
					System.out.println("Enter the SSN of dependent:");
					dSSN=in.next();
					System.out.println("Enter the planID:");
					planID=in.next();
					System.out.println("Enter the dental plan. Type GoodOne or BadOne");
					dental=in.next();
					System.out.println("Enter the vision plan. Type GoodOne or BadOne");
					vision=in.next();
					System.out.println("Enter the life plan. Type GoodOne or BadOne");
					life=in.next();
					String sql= "insert into dependentbenefits values (" + ssn + "," + dSSN + ",'" + planID + "','" + dental +"','" + vision +"','" + life + "')";
					stmt.executeUpdate(sql);
					System.out.println("adding...");
					Thread.sleep(3000);
				}
				if(option==11) {
					String paycheck,ssnLoyer,fo1k,healt,attor,bonus;
					System.out.println("Enter the SSN of employee:");
					ssn=in.next();
					System.out.println("Enter paycheck amount of employee:");
					paycheck=in.next();
					System.out.println("Enter employers contribution to Social Security:");
					ssnLoyer=in.next();
					System.out.println("Enter employers contribution to 401k");
					fo1k=in.next();
					System.out.println("Enter employers contribution to health plan");
					healt=in.next();
					System.out.println("Enter employers contribution to attorney plan");
					attor=in.next();
					System.out.println("Enter the employees bonus");
					bonus=in.next();
					String sql= "insert into employerexpenses values (" + ssn + "," + paycheck + "," + ssnLoyer + "," + fo1k +"," + healt +"," + attor + "," + bonus + ")";
					stmt.executeUpdate(sql);
					System.out.println("adding...");
					Thread.sleep(3000);
				}
				if(option==12) {
					String fedTax,medTax,ssnLoyee,fo1k,healt,attor;
					System.out.println("Enter the SSN of employee:");
					ssn=in.next();
					System.out.println("Enter federal taxrate for employee:");
					fedTax=in.next();
					System.out.println("Enter employees medicare contribution:");
					medTax=in.next();
					System.out.println("Enter employees contribution to Social Security");
					ssnLoyee=in.next();
					System.out.println("Enter employees contribution to 401k");
					fo1k=in.next();
					System.out.println("Enter employers contribution to helth plan");
					healt=in.next();
					System.out.println("Enter employers contribution to attorney plan");
					attor=in.next();
					String sql= "insert into employeeexpenses values (" + fedTax + "," + medTax + "," + ssnLoyee + "," + fo1k +"," + healt +"," + attor + "," + ssn + ")";
					stmt.executeUpdate(sql);
					System.out.println("adding...");
					Thread.sleep(3000);
					}
				
				
			}
			stmt.close(); // close Statement and release resources
			connect.close();
			in.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
