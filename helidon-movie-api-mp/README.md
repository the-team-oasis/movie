# OKE HandsO.....n

### 1. ocicli 설치 및 설정
**설치**
> https://thekoguryo.github.io/oci/chapter14/1/1/1/

**설정**
> https://thekoguryo.github.io/oci/chapter14/1/1/3/

### 2. kubectl 설치
> https://kubernetes.io/ko/docs/tasks/tools/install-kubectl-windows/

### 3. ocicli 설치 및 kubectl 설치 없이 Cloud Shell을 활용
우측 상단의 Cloud Shell 버튼 클릭한 후 오픈된 Cloud Shell에서 실습 진행

### 4. OKE 클러스터 생성
1. OCI Console 접속 후 "우측 상단 햄버거 버튼 클릭 > Developer Services > Kubernetes Cluster" 선택
2. "Create Cluster > Quick Create" 선택
3. Cluster 이름 입력 (e.g. oke-cluster1)
4. 앞서 생성한 Compartment 선택
5. Shape: VM.Standard2.1 (Intel 1 OCPU / 15GB) 혹은 VM.Standard.E4.Flex (AMD EPIC 1 OCPU / 16GB) 선택
6. Next 선택 후 생성

### 5. KUBECONFIG 생성
1. 생성된 클러스터를 클릭한 후 Access Cluster 버튼을 선택
2. 오픈된 팝업창의 가이드 순서대로 진행

### 6. kubectl 테스트
```
$ kubectl get nodes
```

### 7. 샘플 애플리케이션
> https://github.com/MangDan/helidon-movie-api-mp

**소스 다운로드**
```
$ git clone https://github.com/MangDan/helidon-movie-api-mp
```

### 8. 이미지 빌드 및 푸시 (선택)
**이미지 태그 형태**
> {리전코드}.ocir.io/{오브젝트 스토리지 네임스페이스}/{레파지토리명}/{이미지명}:{태그}

```
$ docker build -t icn.ocir.io/idxikmibcwcd/movie/helidon-movie-api-mp:1.0 .

$ docker login icn.ocir.io -u idxikmibcwcd/oracleidentitycloudservice/donghu.kim@oracle.com

password : AFPLga[PL0-Kw]y9(Ri)

$ docker push icn.ocir.io/idxikmibcwcd/movie/helidon-movie-api-mp:1.0
```

### 9. K8s namespace, secret 생성
Auth Token: 우측 상단 "Profile > 사용자 계정 선택 > 좌측 메뉴에서 Auth Token 선택 후 생성"
> Auth Token은 한번 생성 후 다시 확인이 불가능하기 때문에 복사해서 기록

```
$ kubectl create ns movie

$ kubectl create secret docker-registry ocirsecret --docker-server=icn.ocir.io --docker-username=idxikmibcwcd/oracleidentitycloudservice/donghu.kim@oracle.com --docker-password='{Auth Token}' --docker-email=donghu.kim@oracle.com -n movie
```

### 10. Deploy
```
$ cd helidon-movie-api-mp
$ kubectl apply -f kube-helidon-movie-api-mp-config-direct.yml
```

### 11. Pod 확인 및 External IP 확인
```
$ kubectl get all -n movie
```

### 12. 서비스 확인
http://{external_ip}:8080/api/search/v1/movies
