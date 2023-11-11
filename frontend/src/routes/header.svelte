<script>
    import { signIn, signOut } from '@auth/sveltekit/client';
    import {page} from "$app/stores";
</script>

{#if $page.data?.session?.user}
    <img src={$page.data.session.user.image} alt="Avatar" class="avatar" height="100" width="100" />
    {#if $page.data.session.user.email}
        <span>{$page.data.session.user.email}</span>
    {/if}
    <span>{$page.data.session.user.name}</span>
    <button on:click={() => signOut('auth0', {
  redirect: true,
  callbackUrl: '/logout'
})} class="button">Sign out</button>
{/if}
<button on:click={() => signIn(
  'auth0', {
    redirect: false,
    callbackUrl: '/'
  },
  {
    scope: 'api openid profile email'
  }
)}>Sign in</button>
