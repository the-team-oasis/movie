def imageTag = "${params.DOCKER_REGISTRY}/${params.TENANCY}/${params.NAMESPACE}/${params.APPLICATION}:${params.TAG}"

pipeline {
    agent any
        stages {
          stage('Build Image and push') { 
               steps {		
                   sh """
                       docker login -u ${params.REGISTRY_USERNAME} -p '${params.REGISTRY_TOKEN}' ${params.DOCKER_REGISTRY}
                       docker build -t ${imageTag} .
                       docker push ${imageTag} 
                   """
		}
	}
        
        stage('Deploy To Kubernetes'){
          steps{
            withKubeCredentials(kubectlCredentials: [[caCertificate: '', clusterName: 'cluster-ctiuoakdfza', contextName: 'apacckrsct01-oke-cluster-1', credentialsId: 'oke-kubeconfig', namespace: 'kube-system', serverUrl: 'https://152.70.95.226:6443']]) {
                
                
              catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                sh "kubectl create ns  ${params.NAMESPACE}"
              }
              catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                sh "kubectl create secret docker-registry ocirsecret --docker-username='${params.REGISTRY_USERNAME}' --docker-password='${params.REGISTRY_TOKEN}'  --docker-server=${params.DOCKER_REGISTRY} --docker-email='donghu.kim@oracle.com' -n ${params.NAMESPACE}"
              }

                sh "kubectl apply -f kube-helidon-movie-api-mp-config-direct.yml"
            }
          }
        }
    }
}
