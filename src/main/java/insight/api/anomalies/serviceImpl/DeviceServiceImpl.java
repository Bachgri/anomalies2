package insight.api.anomalies.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import insight.api.anomalies.entity.Device;
import insight.api.anomalies.repository.DeviceRepository;
import insight.api.anomalies.service.DeviceService;
 
@Service
public class DeviceServiceImpl implements DeviceService {

	DeviceRepository deviceRepository;
	
	
	public DeviceServiceImpl(DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}

	@Override
	public List<Device> getAll() {
		return deviceRepository.findAll();
	}

	@Override
	public Device post(Device d) {
		return deviceRepository.save(d);
	}

	@Override
	public Device put(Device d) {
		Device dd = deviceRepository.findById(d.getId()).get();
		dd.copyTo(d);
		return deviceRepository.save(dd);
	}

	@Override
	public Device get(Long id) {
		return deviceRepository.findById(id).get();
	}

	@Override
	public Device delete(long d) {
		Device device = deviceRepository.findById(d).get();
		deviceRepository.delete(device);
		return device;
	}

}
