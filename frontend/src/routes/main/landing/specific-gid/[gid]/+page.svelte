<script lang="ts">
    import { page } from '$app/stores';
    let group_id = $page.params.gid;
    let all_info = {
        "short_name": "Joe, Bob, and Your Mom",
        "other_members_info": [
            {
                "name": "Joe Biden",
                "pfpurl": "https://pbs.twimg.com/profile_images/1308769664240160770/AfgzWVE7_200x200.jpg"
            },
            {
                "name": "Bob the Builder",
                "pfpurl": "https://cdn.dribbble.com/users/14550336/screenshots/20330496/media/631486de0e0655a1ee3a30335acc18d2.jpg?resize=300x300&vertical=center"
            },
            {
                "name": "Your Mom",
                "pfpurl": "https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/Trollface_non-free.png/220px-Trollface_non-free.png"
            }
        ]
    };
</script>

<div class="centered-container">
    <h1 class="page-title">{all_info.short_name}</h1>
</div>

<div class="centered-container">
    {#each all_info.other_members_info as info}
        <div class="profile-box">
            <div class="profile-box-inner">
                <!-- profile image -->
                <div class="centered-container">
                    <div class="profile-picture-container">
                        <img src="{info.pfpurl}" alt="Profile" width="200" height="200">
                    </div>
                </div>
                <!-- name -->
                <div class="centered-container">
                    <strong class="profile-name">{info.name}</strong>
                </div>
            </div>
        </div>
    {/each}
    <br/>
</div>

<div class="centered-container">
    <div>
        <button class="group-action-button join-button" on:click={() => {
            document.location += "/action-join";
        }}>JOIN</button>
        <button class="group-action-button reject-button" on:click={() => {
            document.location += "/action-reject";
        }}>REJECT</button>
    </div>
</div>

<div class="at-bottom">
    Group ID: {group_id}
</div>

<style>
    .centered-container {
        display: flex;
        justify-content: center;
    }

    .page-title {
        font-size: 40pt;
    }

    .profile-box {
        margin: 30px;
        float: left;
    }

    .profile-box-inner {
        display: grid;
    }

    .profile-picture-container {
        border-radius: 100%;
        overflow: clip;
    }

    .profile-name {
        font-size: 20pt;
    }

    .group-action-button {
        margin: 20px;
        padding: 5px;
        font-size: 40pt;
    }

    .join-button {
        background-color: lime;
    }

    .reject-button {
        background-color: red;
    }

    .at-bottom {
        position: absolute;
        bottom: 10px;
    }
</style>
