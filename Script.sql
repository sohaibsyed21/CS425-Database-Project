
CREATE TABLE State(
stateName varchar(30),
taxRate float(2),
primary key (stateName)
);

CREATE TABLE Employee(
employeeSSN Numeric(9,0) not null,
firstName varchar(20) NOT NULL,
lastName varchar(20) NOT NULL,
jobTitle varchar(20) NOT NULL,
salaryType varchar(10) NOT NULL,
primary key (employeeSSN),
unique (employeeSSN)
);
alter table Employee add column stateName varchar(20) references State(stateName); 

CREATE TABLE EmployeeExpenses(
federalTaxRate float(2), 
medicareTax float(2),
socialSecurityEmployee float(2),
fourOhOneKayOkEmployee float(2),
healthPlanEmployee float(2),
attorneyPlanEmployee float(2),
employeeSSN numeric(9,0) primary key
REFERENCES Employee(employeeSSN)
ON DELETE CASCADE
);


CREATE TABLE EmployerExpenses(
employeeSSN numeric (9,0) PRIMARY KEY REFERENCES Employee(employeeSSN) ON DELETE cascade,
payCheck float(2),
socialSecurityEmployer float(2),
fourOhOneKayOkEmployer float(2),
healthPlanEmployer float(2),
attorneyPlanEmployer float(2),
bonus int
);

CREATE TABLE Benefits(
employeeSSN numeric (9,0) REFERENCES Employee(employeeSSN) ON DELETE CASCADE,
planID varchar(20) NOT null,
premiumIndividual float(2),
premiumFamily float(2),
fourOhOneKayOk float(2),
healthPlan varchar(20),
attorneyPlan varchar(20),
PRIMARY KEY (employeeSSN,planID),
unique (planID)
);

CREATE TABLE Dependent(
employeeSSN NUMERIC (9,0) REFERENCES Employee(employeeSSN) ON DELETE CASCADE,
dependentSSN NUMERIC (9,0) NOT NULL,
firstName varchar(20) NOT NULL,
lastName varchar(20) NOT NULL,
relation varchar(20) NOT NULL,
PRIMARY KEY (employeeSSN,dependentSSN),
unique (dependentSSN)
);

CREATE TABLE DependentBenefits(
employeeSSN NUMERIC (9,0) REFERENCES Employee(employeeSSN) ON DELETE CASCADE,
dependentSSN NUMERIC (9,0) REFERENCES Dependent(dependentSSN) ON DELETE CASCADE,
planID varchar(20) REFERENCES Benefits(planID) ON DELETE CASCADE,
dentalInsurance varchar(20),
visionInsurance varchar(20),
lifeInusrance varchar(20),
PRIMARY KEY (dependentSSN,employeeSSN,planID)
);

CREATE or replace VIEW paycheck AS select employee.employeeSSN, employerexpenses.payCheck, state.taxRate, employeeexpenses.federalTaxRate, EmployeeExpenses.medicareTax,EmployeeExpenses.socialSecurityEmployee,EmployeeExpenses.fourOhOneKayOkEmployee,benefits.premiumindividual, benefits.premiumfamily from employee left join employerexpenses on employerexpenses.employeessn=employee.employeessn left join employeeexpenses on employeeexpenses.employeessn =employee.employeessn left join benefits on benefits.employeessn =employee.employeessn left join state on state.statename=employee.statename;
CREATE or replace VIEW w2 as select employee.employeeSSN,(EmployerExpenses.payCheck)*24 as yearly_income, state.taxrate as stRate, EmployeeExpenses.federalTaxRate as fedRate,EmployeeExpenses.medicareTax as medTax,EmployeeExpenses.socialSecurityEmployee as SSTax,EmployeeExpenses.fourOhOneKayOkEmployee as k401,EmployeeExpenses.healthPlanEmployee as health_plan,EmployeeExpenses.attorneyPlanEmployee as attorney_plan,employerexpenses.bonus as bonus, ((EmployerExpenses.payCheck)*24) + employerexpenses.bonus - EmployeeExpenses.attorneyPlanEmployee-EmployeeExpenses.healthPlanEmployee-((EmployerExpenses.payCheck)*24*state.taxrate)-(((EmployerExpenses.payCheck)*24)*EmployeeExpenses.federalTaxRate)-(((EmployerExpenses.payCheck)*24)*EmployeeExpenses.medicareTax)-(((EmployerExpenses.payCheck)*24)*EmployeeExpenses.socialSecurityEmployee)-(((EmployerExpenses.payCheck)*24)*EmployeeExpenses.fourOhOneKayOkEmployee) as Gross_Income from employee left join employeeexpenses on employeeexpenses.employeessn =employee.employeessn left join employerexpenses on employerexpenses.employeessn =employee.employeessn left join state on state.statename =employee.statename;
CREATE or replace VIEW CompanyExpenseReport as select Employee.employeeSSN,EmployerExpenses.payCheck, employerexpenses.bonus, EmployerExpenses.socialSecurityEmployer,EmployerExpenses.fourOhOneKayOkEmployer,EmployerExpenses.healthPlanEmployer,EmployerExpenses.attorneyPlanEmployer, EmployerExpenses.payCheck + employerexpenses.bonus+ EmployerExpenses.socialSecurityEmployer+ EmployerExpenses.fourOhOneKayOkEmployer+EmployerExpenses.healthPlanEmployer+EmployerExpenses.attorneyPlanEmployer as Total_Expense_for_employee from employee left join employerexpenses on employee.employeessn = employerexpenses.employeessn;

--create index biweekly_payCheck
--on paycheck(employeeSSN, payCheck, taxRate, federalTaxRate, medicareTax, socialSecurityEmployee, fourOhOneKayOkEmployee, premiumindividual, premiumfamily);

--create index W2_File
--on w2(employeeSSN, yearlyIncome, taxRate,federalTaxRate,medicareTax,socialSecurityEmployee,fourOhOneKayOkEmployee,healthPlanEmployee,attorneyPlanEmployee,bonus);

--create index expenseReport
--on companyEmployeeExpensesReport(employeeSSN,payCheck, bonus,socialSecurityEmployer,fourOhOneKayOkEmployer,healthPlanEmployer,attorneyPlanEmployer);


insert into state values ('Illinois',.04);
insert into state values ('California',.04);
insert into state values ('Alabama',.04);
insert into state values('Florida',.04);
insert into state values('Indiana',.04);
insert into state values('Missouri',.04);
insert into state values('Kentucky',.04);
insert into state values('New Mexico',.04);
insert into state values('Ohio',.04);
insert into state values('Wisconsin',.04);
insert into state values('Michigan',.04);
insert into state values('Oaklahoma',.04);
insert into state values('Arizona',.04);
insert into state values('New York',.04);
insert into state values('New Jersey',.04);
insert into state values('New Hampshire',.04);
insert into state values('Georgia',.04);
insert into state values('Washington',.04);
insert into state values('Maryland',.04);
insert into state values('Virginia',.04);
insert into state values('West Virginia',.04);
insert into state values('North Dakota',.04);
insert into state values('South Dakota',.04);
insert into state values('North Carolina',.04);
insert into state values('South Carolina',.04);
insert into state values('Colorado',.04);
insert into state values('Idaho',.04);
insert into state values('Iowa',.04);
insert into state values('Texas',.04);
insert into state values('Nevada',.04);
insert into state values('Kansas',.04);
insert into state values('Arkansas',.04);
insert into state values('Pennsylvania',.04);
insert into state values('Tennessee',.04);
insert into state values('Vermont',.04);
insert into state values('Alaska',.04);
insert into state values('Hawaii',.04);
insert into state values('Massachusetts',.04);
insert into state values('Mississippi',.04);
insert into state values('New Orleans',.04);
insert into state values('Montana',.04);
insert into state values('Connecticut',.04);
insert into state values('Utah',.04);
insert into state values('Nebraska',.04);
insert into state values('Rhode Island',.04);
insert into state values('Delaware',.04);
insert into state values('Louisiana',.04);
insert into state values('Wyoming',.04);
insert into state values ('Minnesota',.04);
insert into state values('Maine',.04);

insert into employee values(111223333,'saurim','prskalo','employee','hourly','Illinois');
insert into employeeexpenses values(.05,.025,.15,.1,25,1,111223333);
insert into employerexpenses values(111223333,3000,0,.07,250,150,0);
insert into dependent values(111223333,111223334,'sohaib','syed','friend');
insert into dependent values(111223333,111223335,'ivan','khan','son');
insert into benefits values(111223333,'plan123',20,50,300,'goodOne','badOne');
insert into dependentbenefits values(111223333,111223334,'plan123','123Dental','123Vis','123Life');
insert into dependentbenefits values(111223333,111223335,'plan123','123Dental','123Vis','123Life');
insert into benefits values(111223334,'plan12',20,50,300,'goodOne','badOne');
insert into benefits values(111,'plan1',100,150,'goodOne','bad');
insert into dependent values(111223333,111223336,'rigby','mordecai','friend');



CREATE or replace VIEW profile AS SELECT employee.employeeSSN,firstName, lastName, jobTitle, salaryType, employerexpenses.paycheck, bonus, stateName, planID from employee left JOIN benefits on employee.employeessn =benefits.employeessn left join paycheck on benefits.employeessn =paycheck.employeessn left join employerexpenses on employerexpenses.employeessn=employee.employeessn;
create user kd with password '3157';
grant postgres to kd;
