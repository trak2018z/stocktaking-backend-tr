/*
 * Copyright (c) 2017. This file is subject to the terms and conditions defined in file 'LICENSE.txt', which is part of this source code package.
 */

package com.brotherhood.stocktaking.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public abstract class AbstractRepository {
    @PersistenceContext
    protected EntityManager entityManager;
}
