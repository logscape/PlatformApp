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


sql.eachRow("select RESOURCE_NAME,ATTRIBUTE_NAME,TIME_STAMP,ATTRIBUTE_VALUE_NUM, ATTRIBUTE_VALUE_STR from RESOURCE_METRICS WHERE (TIME_STAMP > DateADD(mi, -5, Current_TimeStamp)) AND (ATTRIBUTE_NAME IN ('ls','uit_t','pg','it','numActivity','serverType','agent_control','netcard','hostStatus','close_process','cit_t','agent_control_enhanced','closeReason','cit','r15s','exempt_process','resourceattr','r1m','ut'));"){ row -> 
	GroovyResultSetExtension resultSet = new GroovyResultSetExtension(row)
	ts = row.getAt('TIME_STAMP')
	aString = "$ts" + resultSet.toString().replace("["," ").replace("]","") + "\n"
	pout << aString
}