package ds.ppJS.services;

import ds.ppJS.models.Role;
import ds.ppJS.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        Role role = roleRepository.findByRoleName(name);
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        return role;
    }

    public Role findById(int id) {
        return roleRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("role not found"));
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public List<Role> getRolesByIds(List<Integer> id) {
        return roleRepository.findAllById(id);
    }

    @Transactional
    public void save(Role role) {
         roleRepository.save(role);
    }
}

