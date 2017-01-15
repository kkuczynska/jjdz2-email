FROM jboss/wildfly:10.1.0.Final

ADD target/jbdee.war /opt/jboss/wildfly/standalone/deployments/jbdee.war