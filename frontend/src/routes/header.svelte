<script>
    import User from "./userHeader.svelte";
    import {page} from "$app/stores";
</script>

<div id="header">
    <a href="/">
        <img src="/logoLight.png" alt="logo" height="40px" />
    </a>
    <User />
</div>

<style>
    #header {
        z-index: 1;
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0 1rem;
        height: 4rem;
        background-color: #333;
        color: #fff;
    }
    a {
        color: #fff;
        text-decoration: none;
        font-size: 1.2rem;
    }
</style>
