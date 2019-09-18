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
MIIEogIBAAKCAQEAzwS75IEw7EKRdaRFsCUWtakQtkHP7x76wemj/C7aN/NcqxH2
X+hVWFMhqkObaJ6IvARARsfMBO66eRzi7RHDDbuQKBK7HlOGILSvxd9feVTy3cTZ
jpzlghqrYv2DWBs8HaoB2SJ3RgEYZeaNtsnZBPsre0Tnyg/OmzBpZu1kYH6HJsZ8
TysbMxI1S5FhMn8kLRe6Ddrab5ODVczW3ENXKeCL70wb5oGJmrL4zNkWezbrcXMt
IJUh/kLO+avkLIbZYSIjOOZPIdvzJYqtLRahwkUn9luRjKIDHYJk7I3lEPRASAJt
woRcczmTy/Zp+2crb0uvSH5tSEmN+DR5dCTezwIDAQABAoIBADahqjAYQ9/lhVuF
WXqtnvubvTRL0+LnywcHcGYpux7O02K1383Zk0Q21AiJxOZsB+uON6Q2L8oRchZq
IByt9kqWd+mLX+QV6KyS4lwWoax0VY9e1ocDMrIN9bKu8k17CndNmSVDKjTILzcd
C8L0K5hb7FKgkYbKexyLPxWRik7IO6aWrh/NGCsMjOaw2LjZaSyEcu1+HSz0+XgP
5ea/+SCeK+NyEUc1/1lzjOXVmQiyavkR5Am+02enDjj58tfSAsR1cVUiPqy/9EU1
akFNXVqtLmk6AItql2Hl6nNV0UJmnQbu8/giHNVvL7w+EpWs48XyBL5zxmzXvS+W
C9ZtM4ECgYEA8EWu33WXsSG91u4iL8BBiyCrB0cfp6SwsTfbLiWNN4pwLTnS609/
rqpmCG7EVxfuh6iSHGwKZadTTC4AwEO6nc+igxShTu43RjFOeGCVIB2w4JLSCcn6
6PQdP0XBC19loRQP/eHPZ96Yo9wu/hKLIXMoRhnA9vIW5Lbzi/C748ECgYEA3JHO
2bkoAv/eDqcfTQMTbTxb5DsDzpUIxEbOiNXi3ybIoGfaQBkkXXh3Ho8J4onC3bsZ
vYnTUoNk1UlVrSgX2OQ5Psugdwgwr+Wwg2L9ARodG2BkoeT5+zuvmIahrdUGRk8b
4kuxuqM5HR7lgG3J+VXMAoYjVMlLqUeZqMfiJo8CgYA3Qc9V1NVuBzSBmYKDONPa
C0lKAzFkfJumJhJiKoCvpUbnH8VCGKZkpQuSJOdCoF5bFImFv9MkTbb6bhn+HZVt
dbMKMlRmsqJDolNJm+yv1iQ3gYyMc8cgjAOt6XzcALR+tv52hNgOK0MvbBPimqEk
sDcy5/lY0ZQyRWD9bf/IAQKBgEDXVFnJBqwrD9p64sa++r0IG3NyreHTCiq5Lgon
5nP3cM9G+HATdA9OVKZVInDIUtLgJ+cCV29QVRomXttyh+Ao53NAZUHY1IdBHKzj
MsFaLehYY/z71Uk3+Jz8Jxd1Ik0VAHt8CRl21llrNciVfeSghkLPsB9Ctc1j0RY+
/IqdAoGAb2tiTBcoEm49sr3eKXA/77hOXaufxMFiwibP/JLMonn7Dkt5M9p7rpLy
NdLhVA/CDmxmey9wUbOpPd+MRda753BFU/Xv0VrSOAfFnH+HCSu9pgMhGKnxVg4t
rwTnB7MrSuzKP0EB35353S3g2oUSUduragYPXP+fH3bv+rEKuJg=
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