// per http://dickersonshypotheticalblog.blogspot.com/2011/05/grails-spring-security-using.html
import org.codehaus.groovy.grails.plugins.springsecurity.SecurityFilterPosition
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import hello.*
class BootStrap {

    def init = { servletContext ->
       // per http://dickersonshypotheticalblog.blogspot.com/2011/05/grails-spring-security-using.html
       SpringSecurityUtils.clientRegisterFilter('requestHeaderAuthenticationFilter', SecurityFilterPosition.PRE_AUTH_FILTER)

       // per http://grails.1312388.n4.nabble.com/Spring-Security-PreAuthentication-getting-login-prompt-td2077536.html
       def adminRole = new SecRole(authority: 'ROLE_ADMIN').save(flush: true)
       def userRole = new SecRole(authority: 'ROLE_USER').save(flush: true)
       def testUser = new SecUser(username: 'hellouser', enabled: true, password: '!')
       testUser.save(flush: true)
       SecUserSecRole.create testUser, adminRole, true
       assert SecUser.count() == 1
       assert SecRole.count() == 2
       assert SecUserSecRole.count() == 1
    }
    def destroy = {
    }
}
