package cmm.apps.adevinta.datasource_local.user.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserLocalModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val uuid: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val imageUrl: String?,
    val gender: String,
    val title: String,
    val streetName: String?,
    val streetNumber: Int?,
    val city: String?,
    val state: String?,
    val postcode: String?,
    val country: String?
)