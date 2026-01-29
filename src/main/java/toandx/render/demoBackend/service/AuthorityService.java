package toandx.render.demoBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toandx.render.demoBackend.entity.Authority;
import toandx.render.demoBackend.repository.AuthorityRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    private HashMap<String, Set<Authority>> roleMap;
    private Authority authority;

    public AuthorityService()
    {
        HashMap<String, Authority> authorityMap = new HashMap<String,Authority>();
        for(String name : Arrays.asList("USER", "ADMIN"))
        {
            authority=new Authority(name);
            authorityMap.put(name,authorityRepository.getById(name));
            //authorityRepository.save(authority);
        }
        roleMap = new HashMap<String,Set<Authority>>();

        Set<Authority> authoritySet1=new HashSet<Authority>();
        for(String name: Arrays.asList("USER"))
            authoritySet1.add(authorityMap.get(name));
        roleMap.put("user",authoritySet1); // Role user has USER permission

        Set<Authority> authoritySet2=new HashSet<Authority>();
        for(String name: Arrays.asList("USER","ADMIN"))
            authoritySet2.add(authorityMap.get(name));
        roleMap.put("admin",authoritySet2); // Role admin has USER, ADMIN permission
    }

    public Set<Authority> getAuthority(String role)
    {
        return(roleMap.get(role));
    }
}
