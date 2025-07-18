pipeline {
    agent {
        node {
            label 'T009N_linux_dev'
        }
    }
    environment {
        MAVEN_HOME = tool 'maven-3.8.1'
        ROLE = "testing"
        SERVICE="systemtestautomation"
        https_proxy = "proxy.platform.uk.local:3128"
        no_proxy = "platform.lecp.uk.local"
        env_name = "dev"
    }
    stages {
        stage("SetUp Parameters") {
            steps {
               script {
                   properties([
                      parameters([
                         choice(
                            choices : ['"CHROME"','"EDGE"','"FIREFOX"'],
                            name: 'BROWSER'
                         ),
                         string(
                            defaultValue:'"@entry"',
                            name:'TAGS',
                            trim:true
                         ),
                         choice(
                            choices : ['"v1"'],
                            name:'APIVERSION'
                         ),
                         choice(
                            choices : ['No','Yes'],
                            name:'HEADLESS'
                         )
                         choice(
                            choices : ['No','Yes'],
                            name:'UPLOAD_RESULTS'
                         )
                      ])
                   ]) 
               } 
            }
        }
        stage("Pre-Reqs") {
            steps {
                script {
                    if (params.BROWSER == 'CHROME') {
                        sh """
                        chmod +x drivers/chromedriver
                        wget https://artifactory.platform.uk.local:443/artifactory/tenant-developer-tools/Google-Chrome/Linux/google-chrome-stable_101.0.4951_x86_64.rpm
                        yum -y localinstall google-chrome-stable_101.0.4951_x86_64.rpm
                        """
                    }
                    if (params.BROWSER == 'FIREFOX') {
                        sh """
                        chmod +x drivers/geckodriver
                        wget https://artifactory.platform.uk.local:443/artifactory/platform-developer-tools/Firefox/firefox-118.0.1.tar.bz2
                        tar -xvf firefox-118.0.1.tar.bz2
                        chmod +x firefox/
                        """
                    }
                }
            }
        }
        stage("Run Tests") {
            environment {
                JAVA_HOME = tool "jdk-17.0.5"
            }
            steps {
                withVault(configuration: [engineVersion: 2, timeout: 60, vaultCredentialId: "${env_name}-vault", vaultNamespace: 'ABC', vaultUrl: 'https://vault.platform.uk.local:8200'], vaultSecrets: [
                [path: "${env_name}-kv/config-store/ui/frontend/hostname", secretValues: [
                    [envVar: 'UI_FRONTEND_HOST', vaultKey: 'hostname']
                ]],
                [path: "${env_name}-kv/config-store/updategram/service/hostname", secretValues: [
                    [envVar: 'UPDATEGRAM_SERVICE_HOST', vaultKey: 'hostname']
                ]],
                [path: "${env_name}-kv/config-store/file/api/hostname", secretValues: [
                    [envVar: 'FILE_API_HOST', vaultKey: 'hostname']
                ]],
                [path: "${env_name}-kv/config-store/search/searchrequestmanager/hostname", secretValues: [
                    [envVar: 'SEARCH_SEARCHREQUESTMANAGER_HOST', vaultKey: 'hostname']
                ]],
                [path: "${env_name}-kv/config-store/search/searchresultmanager/hostname", secretValues: [
                    [envVar: 'SEARCH_SEARCHRESULTMANAGER_HOST', vaultKey: 'hostname']
                ]],
                [path: "${env_name}-kv/config-store/abc/db/hostname", secretValues: [
                    [envVar: 'DB_HOST', vaultKey: 'hostname']
                ]],
                [path: "${env_name}-kv/config-store/abc/db/port", secretValues: [
                    [envVar: 'DB_PORT', vaultKey: 'port']
                ]],
                [path: "${env_name}-kv/config-store/audit/db/name", secretValues: [
                    [envVar: 'AUDIT_DB_NAME', vaultKey: 'name']
                ]],
                [path: "${env_name}-kv/config-store/audit/db/owner-username", secretValues: [
                    [envVar: 'AUDIT_DB_USER', vaultKey: 'owner-username']
                ]],
                [path: "${env_name}-kv/config-store/audit/db/owner-password", secretValues: [
                    [envVar: 'AUDIT_DB_PASS', vaultKey: 'owner-password']
                ]]]) {
                    withCredentials([usernamePassword(credentialsId: 'Artifactory', passwordVariable: 'ARTIFACTORY_PASSWORD', usernameVariable: 'ARTIFACTORY_USER')]) {
                        sh """
                            envsubst < env_config/dev.properties > env_config/dev.properties.new
                            mv env_config/dev.properties.new env_config/dev.properties
                            $MAVEN_HOME/bin/mvn clean install \
                                -s settings.xml \
                                -f pom.xml \
                                -Dcucumber.filter.tags=$params.TAGS \
                                -Denv="${env_name}" \
                                -DapiVersion=$params.APIVERSION \
                                -Dbrowser=$params.BROWSER \
                                -Dheadless="$params.HEADLESS \
                                -Dmaven.wagon.http.ssl.insecure=true ##TODO: Fix and remove
                        """
                    }
                }
            }
        }
        stage("Trigger Upload to S3") {
            when {
                expression {params.UPLOAD_RESULTS == 'Yes'}
            }
            steps {
                script {
                    sh """
                    aws s3 sync "${WORKSPACE}/reportsNewStyle/" s3://abc-dev-abc-testonebucket --sse aws:kms
                    aws s3 sync "${WORKSPACE}/reports/" s3://abc-dev-abc-testonebucket --sse aws:kms
                    aws s3 sync "${WORKSPACE}/logs/" s3://abc-dev-abc-testonebucket --sse aws:kms 
                    aws s3 sync "${WORKSPACE}/target/cucumber-reports/advanced-reports/" s3://abc-dev-abc-testonebucket --sse aws:kms
                    sh """
                }
            }
        }
    }
}
