package shop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Byte> {

}
