package org.example;

import java.sql.*;
import java.util.Properties;

import software.aws.rds.jdbc.mysql.shading.com.mysql.cj.jdbc.ha.plugins.AWSSecretsManagerPluginFactory;

/**
 * https://github.com/awslabs/aws-mysql-jdbc#aws-secrets-manager-plugin
 *
 * Exception in thread "main" software.amazon.awssdk.core.exception.SdkClientException: Unable to load credentials from any of the providers in the chain AwsCredentialsProviderChain(credentialsProviders=[SystemPropertyCredentialsProvider(), EnvironmentVariableCredentialsProvider(), WebIdentityTokenCredentialsProvider(), ProfileCredentialsProvider(profileName=default, profileFile=ProfileFile(profilesAndSectionsMap=[])), ContainerCredentialsProvider(), InstanceProfileCredentialsProvider()]) : [SystemPropertyCredentialsProvider(): Unable to load credentials from system settings. Access key must be specified either via environment variable (AWS_ACCESS_KEY_ID) or system property (aws.accessKeyId)., EnvironmentVariableCredentialsProvider(): Unable to load credentials from system settings. Access key must be specified either via environment variable (AWS_ACCESS_KEY_ID) or system property (aws.accessKeyId)., WebIdentityTokenCredentialsProvider(): Either the environment variable AWS_WEB_IDENTITY_TOKEN_FILE or the javaproperty aws.webIdentityTokenFile must be set., ProfileCredentialsProvider(profileName=default, profileFile=ProfileFile(profilesAndSectionsMap=[])): Profile file contained no credentials for profile 'default': ProfileFile(profilesAndSectionsMap=[]), ContainerCredentialsProvider(): Cannot fetch credentials from container - neither AWS_CONTAINER_CREDENTIALS_FULL_URI or AWS_CONTAINER_CREDENTIALS_RELATIVE_URI environment variables are set., InstanceProfileCredentialsProvider(): Failed to load credentials from IMDS.]
 */
public class SecretManagerConnector {
    private static final String CONNECTION_STRING = "jdbc:mysql:aws://[url]:[port]";

    private static final String SECRET_ID = "[secretId]";
    private static final String REGION = "[region]";

    public static void main(String[] args) throws SQLException {
        final Properties properties = new Properties();
        // Enable the AWS Secrets Manager Plugin:
        properties.setProperty("connectionPluginFactories", AWSSecretsManagerPluginFactory.class.getName());

        // Set these properties so secrets can be retrieved:
        properties.setProperty("secretsManagerSecretId", SECRET_ID);
        properties.setProperty("secretsManagerRegion", REGION);

        // Try and make a connection:
        try (final Connection conn = DriverManager.getConnection(CONNECTION_STRING, properties);
             final Statement statement = conn.createStatement();
             final ResultSet rs = statement.executeQuery("SELECT CURRENT_DATE() FROM DUAL")) {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
    }


}
