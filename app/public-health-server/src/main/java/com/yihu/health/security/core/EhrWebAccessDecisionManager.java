package com.yihu.health.security.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Final AccessDecisionManager
 * Created by progr1mmer on 2018/1/26.
 */
public class EhrWebAccessDecisionManager extends AbstractAccessDecisionManager {

    private final Log logger = LogFactory.getLog(this.getClass());
    private List<AccessDecisionVoter<? extends Object>> decisionVoters;
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private boolean allowIfAllAbstainDecisions = false;

    public EhrWebAccessDecisionManager(List<AccessDecisionVoter<? extends Object>> decisionVoters) {
        super(decisionVoters);
        Assert.notEmpty(decisionVoters, "A list of AccessDecisionVoters is required");
        this.decisionVoters = decisionVoters;
    }

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

    }

    public void afterPropertiesSet() throws Exception {
        Assert.notEmpty(this.decisionVoters, "A list of AccessDecisionVoters is required");
        Assert.notNull(this.messages, "A message source must be set");
    }

    public List<AccessDecisionVoter<? extends Object>> getDecisionVoters() {
        return this.decisionVoters;
    }

    public boolean isAllowIfAllAbstainDecisions() {
        return this.allowIfAllAbstainDecisions;
    }

    public void setAllowIfAllAbstainDecisions(boolean allowIfAllAbstainDecisions) {
        this.allowIfAllAbstainDecisions = allowIfAllAbstainDecisions;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    public boolean supports(ConfigAttribute attribute) {
        Iterator var2 = this.decisionVoters.iterator();

        AccessDecisionVoter voter;
        do {
            if(!var2.hasNext()) {
                return false;
            }

            voter = (AccessDecisionVoter)var2.next();
        } while(!voter.supports(attribute));

        return true;
    }

    public boolean supports(Class<?> clazz) {
        Iterator var2 = this.decisionVoters.iterator();

        AccessDecisionVoter voter;
        do {
            if(!var2.hasNext()) {
                return true;
            }

            voter = (AccessDecisionVoter)var2.next();
        } while(voter.supports(clazz));

        return false;
    }
}
