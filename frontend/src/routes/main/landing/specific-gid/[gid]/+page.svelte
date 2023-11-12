<script lang="ts">
    import { page } from '$app/stores';
    let group_id = $page.params.gid;
    let all_info = {
        "short_name": "Joe, Bob, and Dave",
        "other_members_info": [
            {
                "name": "Joe",
                "pfpurl": "https://pbs.twimg.com/profile_images/1308769664240160770/AfgzWVE7_200x200.jpg"
            },
            {
                "name": "Bob",
                "pfpurl": "https://img.freepik.com/free-photo/asian-man-isolated-expressing-emotions_1303-26709.jpg?size=626&ext=jpg&ga=GA1.1.1826414947.1699747200&semt=ais"
            },
            {
                "name": "Dave",
                "pfpurl": "https://media.discordapp.net/attachments/1172945528615612447/1173346567370047538/czNmcy1wcml2YXRlL3Jhd3BpeGVsX2ltYWdlcy93ZWJzaXRlX2NvbnRlbnQvMzY2LW1ja2luc2V5LTIxYTc3MzYtZm9uLWwtam9iNjU1LnBuZw.png?ex=65639f25&is=65512a25&hm=7324597f9a5f32337af1fb427c00ceb4987d5183c55a140c98d8d25641ddeb15&=&width=571&height=571"
            }
        ]
    };
    let meet_start_time = "2:00";
    let meet_end_time = "3:30";
    let meet_ampm = "PM";
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
    <div class="available-to-meet">
        Available to meet {meet_start_time}-{meet_end_time} {meet_ampm}.
    </div>
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

    .available-to-meet {
        background-color: lightskyblue;
        border-radius: 6px;
        font-size: 30pt;
        margin: 10px;
        padding: 10px;
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
