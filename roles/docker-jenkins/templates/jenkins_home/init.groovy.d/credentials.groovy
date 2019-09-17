import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;

String keyfile = "/tmp/key"

// Azure
Credentials azure_credential = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL,"azureCredentialId", "Credencial do Azure DevOps", "azure", "hcz7vm32sn4y4ufo7kbhaicd7etvsgsiuqr76e5b625mj2nahyqq")

SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), azure_credential)
