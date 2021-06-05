# Login service using OpenID Connect for authentication/OAuth2 for authorization
OpenID Connect is the third generation of OpenID technology. 
 
 It is an authentication layer on top of the OAuth 2.0 authorization framework.
 
 It allows computing clients to verify the identity of an end-user based on the authentication performed by an authorization server, as well as to obtain the basic profile information about the end-user in an interoperable and REST-like manner.
 
# Setup okta account
Register a okta account

setup a default scope

a Login Redirect of http://localhost:8080/login/oauth2/code/okta 
and a Logout Redirect of http://localhost:8080, 

# Authrization flow
![alt text](https://developer.okta.com/img/oauth_auth_code_flow.png)

(reference from: https://developer.okta.com/docs/concepts/oauth-openid/#authorization-code-flow)
