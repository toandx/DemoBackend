package toandx.render.demoBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toandx.render.demoBackend.entity.Authority;
import toandx.render.demoBackend.repository.AuthorityRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    public Set<Authority> getAuthority(String role)
    {
        Authority userAuth = authorityRepository.getById("USER");
        Authority adminAuth = authorityRepository.getById("ADMIN");
        Set<Authority> authoritySet=new HashSet<Authority>();
        authoritySet.add(userAuth);
        if ("admin".equals(role)) {
            authoritySet.add(adminAuth);
            return authoritySet;
        }
        return authoritySet;
    }
}
