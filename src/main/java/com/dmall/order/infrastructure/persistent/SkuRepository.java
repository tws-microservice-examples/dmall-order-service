package com.dmall.order.infrastructure.persistent;

import com.dmall.order.domain.model.SkuSnapShot;
import org.springframework.data.repository.CrudRepository;

public interface SkuRepository extends CrudRepository<SkuSnapShot, Long>{
}
