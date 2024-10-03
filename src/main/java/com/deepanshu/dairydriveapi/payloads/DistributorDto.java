package com.deepanshu.dairydriveapi.payloads;

import com.deepanshu.dairydriveapi.entities.Distributor;
import com.deepanshu.dairydriveapi.utilities.UsersRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DistributorDto {
    private String id;

    @Email
    private String email;

    @NotNull
    @NotEmpty
    @JsonIgnore
    @Size(min = 5, message = "At least have 5 characters.")
    private String password;

    @NotNull
    @Max(value = 9999, message = "Should have 4 digits")
    @Min(value = 1000, message = "Should have 4 digits")
    private Integer deliveryCode;

    @NotNull
    private UsersRole role;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100, message = "At least have 3 letters.")
    private String name;

    @NotNull
    @Pattern(regexp = "[0-9]{10}", message = "Must contain 10 digits only.")
    private String contact;

    private int daysRemaining;// Number of days left for out of valid plan.
    private boolean isDeleted;
    private String emailToken;
    private boolean isVerified;

    public static Distributor dto2Model(DistributorDto dto, ModelMapper modelMapper)
    {
        return modelMapper.map(dto, Distributor.class);
    }
    public static DistributorDto model2Dto(Distributor model, ModelMapper modelMapper)
    {
        return modelMapper.map(model, DistributorDto.class);
    }
}
