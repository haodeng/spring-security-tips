The resource server is the OAuth 2.0 term for your API server. 

The resource server handles authenticated requests after the application has obtained an access token.

# Demo
* run okta resource server application on port 8081
* run OpenIdConnectAuthApplication on port 8080

Test

    localhost:8080/claims
    login to Okta
    
    localhost:8080/something