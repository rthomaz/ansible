import hudson.plugins.sshslaves.*
  
import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.Domain;
import com.cloudbees.plugins.credentials.domains.DomainRequirement;
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl;
  
// get Jenkins instance
Jenkins jenkins = Jenkins.getInstance()

// get credentials domain
def domain = Domain.global()

// get credentials store
def store = SystemCredentialsProvider.getInstance().getStore()


Credentials azureCredential = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL,
                                    "azureCredentialId", 
                                    "Credencial do Token do Azure DevOps", 
                                    "azure", 
                                    "hcz7vm32sn4y4ufo7kbhaicd7etvsgsiuqr76e5b625mj2nahyqq")
                                    
store.addCredentials(domain, azureCredential)

// save to disk
jenkins.save()