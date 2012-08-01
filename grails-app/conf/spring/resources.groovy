// Place your Spring DSL code here


//see:  http://grails.1312388.n4.nabble.com/Spring-Security-PreAuthentication-getting-login-prompt-td2077536.html
import org.codehaus.groovy.grails.plugins.springsecurity.GormUserDetailsService
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper


beans = {

   // per:  http://dickersonshypotheticalblog.blogspot.com/2011/05/grails-spring-security-using.html
   // regarding:  Message: remoteUser header not found in request.
   // see http://grails.1312388.n4.nabble.com/Spring-Security-PreAuthentication-getting-login-prompt-td2077536.html
   userDetailsService(org.codehaus.groovy.grails.plugins.springsecurity.GormUserDetailsService) {
      grailsApplication = ref('grailsApplication')
   }

   //removed:
   //   see:  http://grails.1312388.n4.nabble.com/Spring-Security-PreAuthentication-getting-login-prompt-td2077536.html
   //userDetailsByNameServiceWrapper(org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper) {
   //   userDetailsService = ref('userDetailsService')
   //}
  
   //preAuthenticatedAuthenticationProvider(org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider) {
   // preAuthenticatedUserDetailsService = userDetailsByNameServiceWrapper
   //}

   //replace above with:
   //   see:  http://grails.1312388.n4.nabble.com/Spring-Security-PreAuthentication-getting-login-prompt-td2077536.html
   preAuthenticatedAuthenticationProvider(org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider) {
    preAuthenticatedUserDetailsService = new UserDetailsByNameServiceWrapper(new GormUserDetailsService()) //Tried to wrap ref(?userDetailsService?) here, but it won?t run
  }

   requestHeaderAuthenticationFilter(org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter) {
      principalRequestHeader = 'remoteUser'
      authenticationManager = ref('authenticationManager')
   }


}
