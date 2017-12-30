package com.dmall.order.z.debug.infrastructure.persistent;

import com.dmall.order.domain.model.SkuSnapShot;
import org.springframework.data.repository.CrudRepository;

public interface SkuRepositoryDebug extends CrudRepository<SkuSnapShot, Long>{
}
