import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import groovy.sql.Sql;
import groovy.sql.GroovyResultSetExtension;

def propertyMissing(String name) {}

pout = pout == null ? System.out : pout
perr = perr == null ? System.err : perr

def stdOut = pout
def stdErr = perr

def sql = Sql.newInstance("jdbc:sqlserver://10.28.1.7:49187", "SYMPHONY", "Blah!Blah!Blah!")

sql.eachRow("select * FROM SESSION_HISTORY WHERE TIME_STAMP > DateADD(mi, -34800, DateADD(mi, -5, Current_TimeStamp)) AND TIME_STAMP < DATEADD(mi,--5,Current_Timestamp);"){ row -> 
	GroovyResultSetExtension resultSet = new GroovyResultSetExtension(row)
	//ts = row.getAt('TIME_STAMP')
	ts = new Date()
	aString = "$ts" + resultSet.toString().replace("["," ").replace("]","") + "\n"
	pout << aString
}

