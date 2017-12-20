node{
    step([$class: 'WsCleanup'])

    stage('Checkout') {
        step([$class: 'WsCleanup'])
        git url: 'git@gitee.com:tws-micro-service/dmall-order-service.git', branch: 'master'
    }

    stage('Build') {
        sh './gradlew clean build'
    }

    stage('Check') {
        parallel (
            "Findbugs" : {
                echo 'Findbugs is finished.'
            },
            "Checkstyle" : {
                echo 'Checkstyle is finished.'
            },
            "PMD" : {
                echo 'PMD is finished.'
            }
        )
    }

    stage('Test') {
       sh './gradlew test'
    }

    stage('Docker image') {
        sh './genImages.sh'
    }

    stage('Deploy to DEV') {
        sh './deployToDEV.sh'
    }
}