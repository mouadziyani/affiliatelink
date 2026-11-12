package ma.youcode.affiliatelink.offer.listener;

import lombok.RequiredArgsConstructor;
import ma.youcode.affiliatelink.offer.service.CampaignServices;
import ma.youcode.affiliatelink.profile.ProfileSuspended;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileSuspendedListener {
    private final CampaignServices campaignServices;

    @ApplicationModuleListener
    public void on(ProfileSuspended event) {
        campaignServices.pauseActiveForAdvertiser(event.profileId());
    }
}
