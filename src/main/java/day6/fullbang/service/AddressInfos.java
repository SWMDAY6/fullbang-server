package day6.fullbang.service;

import java.util.ArrayList;
import java.util.List;

import day6.fullbang.dto.addressInfo.AddressInfoDto;
import lombok.Getter;

public class AddressInfos {

    @Getter // TODO refactor
    private final List<AddressInfoDto> addressInfoDtos;

    public boolean addressCodeHeadExist(String addressCodeHead) {

        for (AddressInfoDto addressInfoDto : addressInfoDtos) {
            if (addressInfoDto.getAddressCodeHead().equals(addressCodeHead)) {
                return true;
            }
        }

        return false;
    }

    public AddressInfos(List<AddressInfoDto> addressInfoDtos) {

        this.addressInfoDtos = new ArrayList<>();

        addressInfoDtos.forEach(addressInfoDto -> {
            if (!addressCodeHeadExist(addressInfoDto.getAddressCodeHead())) {
                this.addressInfoDtos.add(addressInfoDto);
            }
        });
    }
}
