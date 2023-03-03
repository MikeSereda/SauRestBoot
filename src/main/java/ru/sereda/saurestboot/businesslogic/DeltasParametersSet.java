package ru.sereda.saurestboot.businesslogic;

import java.time.LocalDateTime;

public class DeltasParametersSet extends ReducedParameterSet{
    public DeltasParametersSet(float ebNoDelta, float ebNoRemoteDelta, LocalDateTime timestampWotz) {
        super(ebNoDelta, ebNoRemoteDelta, timestampWotz);
    }
}
