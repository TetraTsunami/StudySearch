// <3 https://blog.aakashgoplani.in/sveltekit-authentication-using-sveltekitauth-and-oauth-providers-a-comprehensive-guide
import { SvelteKitAuth, type SvelteKitAuthConfig } from "@auth/sveltekit";
import Auth0Provider from "@auth/core/providers/auth0";
import type { Provider } from "@auth/core/providers";
import type { Handle } from "@sveltejs/kit";
import { AUTH0_DOMAIN, AUTH0_CLIENTID, AUTH0_SECRET, AUTHJS_SECRET } from "$env/static/private"

const config: SvelteKitAuthConfig = {
    providers: [
        Auth0Provider({
            // id: "auth0",
            // name: "Auth0",
            clientId: AUTH0_CLIENTID,
            clientSecret: AUTH0_SECRET,
            issuer: AUTH0_DOMAIN,
            // wellKnown: AUTH0_DOMAIN + ".well-known/openid-configuration"
        }) as Provider
    ],
    secret: AUTHJS_SECRET,
    // debug: true,
    // session: {
    //     maxAge: 1800 // 30 mins
    // }
};

export const handle = SvelteKitAuth(config) satisfies Handle;
