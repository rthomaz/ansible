#!groovy

// imports

import hudson.plugins.sshslaves.*
  
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey;
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.domains.DomainRequirement;
import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl;

import hudson.util.Secret
import java.nio.file.Files
import jenkins.model.Jenkins
import net.sf.json.JSONObject
import org.jenkinsci.plugins.plaincredentials.impl.*

// get Jenkins instance
Jenkins jenkins = Jenkins.getInstance()

// get credentials domain
def domain = Domain.global()

// get credentials store
def store = SystemCredentialsProvider.getInstance().getStore()


// **********************************************************
// Azure Credential
// **********************************************************

Credentials azureCredential = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL,
                                    "azureCredentialId", 
                                    "Credencial do Token do Azure DevOps", 
                                    "azure", 
                                    "hcz7vm32sn4y4ufo7kbhaicd7etvsgsiuqr76e5b625mj2nahyqq")
                                    
store.addCredentials(domain, azureCredential)

// **********************************************************
// Docker Hub Credential
// **********************************************************

Credentials dockerHubCredential = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL,
                                    "dockerHubCredentialId", 
                                    "Credencial do Docker Hub", 
                                    "rthomaz", 
                                    "b3b3xu!@#")
                                    
store.addCredentials(domain, dockerHubCredential)

// **********************************************************
// Docker01
// **********************************************************

// parameters
def jenkinsMasterKeyParameters = [
  description:  'Credencial SSH do Docker01',
  id:           'docker01CredentialId',
  secret:       null,
  userName:     'jenkins',
  key:          new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource('''-----BEGIN RSA PRIVATE KEY-----
Proc-Type: 4,ENCRYPTED
DEK-Info: AES-128-CBC,DE9B52BC11FD2BE94E6A49E541F6BF5F

b5lHygopBfa8nXzs6HprlAlybQ5e97yqoRy2/9t1qC9KPqkuddtc1LzyTUSCMBCE
HwNoC895ZM/ky+fUgU/SalkwHDWxIrBjHV7JsasOlyK/ohnO/ds4mmir22L2MQhC
6Z03zUZDgQEiCPR9uR5r1DA0Zb8IGpt0jzXL34fWEZhO4EaI3kmrvfzKbJCcUaWe
tlZv2f1P9SM//diZkDJKFQKvkPViY2pF2yP0SJVx7STmphDPMeodciiq57mCKy8W
WSA2WXfXu1CuUdgyyvrgNJ99FeNd5YwRdHZVksYKEyTUFu/x57WC1cLNERTuI2rE
yMg3ygEY9/2DV4vHLGIZG79qr7fXasWTeN+TqQVH+CbedEsF1Q1eOz7JQOC5xC/h
Z6KUzM+dlhWP8dgESnZnqg8TPg7giazj5+zwaMiYGD8JZfLT4k/6D2O2Jm4i64ny
sF4Nl1EBLonuh2+YgImemqATr8m4h41Xy+Bev81ukjOt9keNad0fCzSYH3Ae2fiL
i6X1NtoxnXZoF+Xj8tPoR97DXautQg8R/07QdElOC7watGLtuX79gNIDPBcrQS8T
il0IjXfR3KYK/ucVxCGRDdEuKL2/anfB0JJ8pxfc+oa69fU8S6x5W/Lxo2WHu48Y
8m3fKdCcCulIBd8MxV/0ec7IqSGDKrXjJI+qKdSHrP+f9nK/lJgFCRWCIuQz8SyW
TgcEPYQ7KyHykyTz9uBA1y2+PyMEVTZTpmeUtZzFTrjBefAXK2r3RLEvlbXxbkR+
lrysiDyGP5GvjHwqcAVKFM0VxzyNnUpgdWQpM160t6Fw9e4Pq/xAQhzA8ePcTto9
fPpz8sDAYxTxQjAm4oMyG4BBRXznkl6nBfUz5pAWiDWOTuDZtc/QPRJlkxn0OON5
fDRJOz/KYSArjGVEElD8dYS5+sHvF7ePlJWvpBIi+JJ4Hqx7Vvc6Q2gp8IX61KLd
hD8qVPHwuqCNp2xZl9b3dG7jVGIEGK+18zHnBzLWy/PI5D+bFhwwGaVgXvg/iuMf
MBXQ5EyfQCQJ+00tO4l/v8yCBwK6xJx43QGXrw/UcpzFi0/X6Tp8264BsDlZCQKk
pk72/sa/6f97fck1oKiwOzPjW+a/KxSvvSoPXPMfp8LAcPLwRgEenjp036ns6ThT
uqDiv4udqqv8tfWsM23Mgx2FuHInBs+Q3GHUqSbaIjeeOGPZRzAbuJmzljGs3Hvu
1FLyUJUFxhuOfnY5lt3bCagVJ/5q5SiFr0nrGUhlKUWmY6BnnaHkOd5SoBKXzDQx
X03rvEtNUwBWEIJC8b6V+uK99RwMsVbHJSlYBSETYEh3HHzWaPSRumS/hKE9DZE6
EwsxsRRHvMBGEdawd7aS1DqjeH2Hgbo2UMtmFV2W69aqnHbHB14/hm3N3PRv7XPb
qkWJjgC3sG/ySm0d1+dz5XhJ6qbYHyEFf6tVwrV0Ip1/smwZHwy8CLYYujJQhhMB
QmiJrEERIFhXyUGetnZ785bSG6wGQCvZWiUA+/18XmsusQ+0HSA+UVd3dsQyKy3Z
OrLLZV2eYg+QrhBWtFhNLZKxF6MulE9bh6SPNr3QxPF45uAYwdEvf714q7klbg8N
-----END RSA PRIVATE KEY-----''')
]


// define private key
def privateKey = new BasicSSHUserPrivateKey(
  CredentialsScope.GLOBAL,
  jenkinsMasterKeyParameters.id,
  jenkinsMasterKeyParameters.userName,
  jenkinsMasterKeyParameters.key,
  jenkinsMasterKeyParameters.secret,
  jenkinsMasterKeyParameters.description
)

// add credential to store
store.addCredentials(domain, privateKey)


// **********************************************************
// save to disk
// **********************************************************

jenkins.save()