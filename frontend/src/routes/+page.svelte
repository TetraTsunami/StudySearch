<script>
    import {signIn} from "@auth/sveltekit/client";
</script>

<!-- front page for app where we discuss benefits of product, and have a call to action to sign up
product helps you find study groups, navbar is already there-->
<div class="jumbotron">
    <h1>StudySearch</h1>
    <h2>Study Group Finder</h2>
    <ul>
        <li>Find study groups for your classes</li>
        <li>Meet new people</li>
        <li>Get better grades</li>
    </ul>
    <p><a on:click={() => signIn(
  'auth0', {
    redirect: false,
    callbackUrl: '/'
  },
  {
    scope: 'api openid profile email'
  }
)} class="authbutton" role="button">Sign Up</a></p>
    <div class="background"></div>
</div>

<style>
    .jumbotron {
        position: relative;
        flex-grow: 1;
        padding: 7rem;
        overflow: clip;
    }
    h1 {
        font-size: 5rem;
    }
    h2, p {
        margin: 1rem 0;
    }
    .background {
        background-image: url("/header.webp");
        background-size: cover;
        position: absolute;
        inset: -5px;
        z-index: -1;
        filter: blur(3px);
        opacity: 0.6;
    }
    .authbutton {
        background-color: var(--accent-color);
        border-radius: 1rem;
        border: none;
        color: black;
        padding: 20px 40px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        cursor: pointer;
    }

</style>
