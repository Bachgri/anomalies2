package insight.api.anomalies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import insight.api.anomalies.entity.Device;
 

public interface DeviceRepository extends JpaRepository<Device, Long> {
	
}
