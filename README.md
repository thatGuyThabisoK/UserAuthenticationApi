# UserAuthenticationApi
A Spring boot web application with a single Post mapping endpoint to "/authenticate", this implementation provides it's own UserDetailsService to retrieve the stored users from our txt file and <a href="https://github.com/thatGuyThabisoK/UserAuthenticationApi/blob/main/java/com/webandsecurity/JwtSecurity/CustomAuthentication.java">CustomAuthentionProvider</a> that will use our UserDetailsService for user retrieval, the user provides their details in the request body when they hit our endpoint
example payload from the user
```
{
  "username": yourname,
  "password": 1230234
}
```
The AuthenticationManager will use the CustomAuthentication provider implementation to authenticate the user in storage, in this instance the user is stored in a txt file.
Once the user is successfully authenticated the endpoint will return a a JSON Web Token containing the users information generated from the defined <a href="https://github.com/thatGuyThabisoK/UserAuthenticationApi/blob/main/java/com/webandsecurity/JwtSecurity/JwtUtil.java">JwtUtil class</a> along with a 200 status code.

# JwtUtil Class
##  This class will contain methods to:

> ``Generate a JWT``: Create a token containing user information (e.g., username, roles) and set an expiration time. ``Validate a JWT``: Verify the token's signature and expiration. ``Extract Information from a JWT``: Retrieve user details or claims from the token.

## Public Methods
### generateToken(UserDetails userDetails) 
This method takes the provided users details and creates a String token containing their information, the following code snippet provides a simple usage for an authenticated user
```
UserDetails authenticated = customDetails.loadUserByUsername(userToAuth.getName());
String token = jwt.generateToken(authenticated);
Map<String, String> response = new HashMap<>();
response.put("token", token);
```
###  validateToken(String token, UserDetails userDetails)
the validateToken function returns a boolean value when the user details are validated against the stored details in the provided JWT string
### isTokenExpired(DecodedJWT token)
Check whether the provided token is still valid
and so on and so forth........

