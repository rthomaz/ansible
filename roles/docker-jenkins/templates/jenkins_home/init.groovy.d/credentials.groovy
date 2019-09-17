import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;

// get credentials domain
def domain = Domain.global()

// get credentials store
def store = SystemCredentialsProvider.getInstance().getStore()

// Azure
println "--> Create credentials for Token Azure"
Credentials azureCredential = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL,
                                    "azureCredentialId", 
                                    "Credencial do Token do Azure DevOps", 
                                    "azure", 
                                    "hcz7vm32sn4y4ufo7kbhaicd7etvsgsiuqr76e5b625mj2nahyqq")
store.addCredentials(domain, azureCredential)

// SSH Docker01

println "--> Create ssh credentials for Docker01"
creds = new BasicSSHUserPrivateKey(CredentialsScope.GLOBAL,
                                    "docker01CredentialId",
                                    "jenkins",
                                    new BasicSSHUserPrivateKey.FileOnMasterPrivateKeySource("~/.ssh/id_rsa"),
                                    "definirumasenha",
                                    "Credencial SSH do Docker01")
store.addCredentials(domain, creds)