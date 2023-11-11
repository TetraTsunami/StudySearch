<script lang="ts">
    import { onMount } from 'svelte';
    import { page } from '$app/stores';

    onMount(async () => {
        const idToken = $page.data?.session?.user.id_token as string;
        window.location.href =
            import.meta.env.VITE_ISSUER +
            `oidc/logout?post_logout_redirect_uri=${encodeURIComponent(
                window.location.origin
            )}&id_token_hint=${idToken}`;
    });
    $page.set({ path: '/' });
</script>


