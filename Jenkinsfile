node {
   stage('Clone') {
      checkout scm
   }

   stage('Build') {
       sh "mvn clean install -U"
   }

   stage('Archive') {
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
   }

   stage('Uploading to Nexus') {
      pom = readMavenPom file: 'pom.xml'
      nexusPublisher nexusInstanceId: 'prnex', nexusRepositoryId: 'bridge', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: "target/${pom.name}.jar"]], mavenCoordinate: [artifactId: pom.artifactId, groupId: 'rip.bridge', packaging: 'jar', version: pom.version]]]
   }
}
