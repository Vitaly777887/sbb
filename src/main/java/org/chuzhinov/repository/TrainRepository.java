package org.chuzhinov.repository;

import org.chuzhinov.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {
    Train getByNumberTrain(int numberTrain);
}
