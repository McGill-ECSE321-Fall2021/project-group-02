<template>
  <transition name="modal-fade">
    <div class="modal-backdrop">
      <div class="modal" role="dialog" aria-labelledby="modalTitle" aria-describedby="modalDescription">
        <header class="modal-header" id="modalTitle" >
          <slot name="header">
            This is the default tile!
          </slot>
          <button type="button" class="btn-close" @click="close" aria-label="Close modal"> x </button>
        </header>

        <section class="modal-body" id="modalDescription" >
          <slot name="body">
            This is the default body!
          </slot>
        </section>

        <footer class="modal-footer">
          <slot name="footer">
          </slot>
          <button type="button" class="btn-green" @click="close" aria-label="Close modal"> Close </button>
        </footer>
      </div>
    </div>
  </transition>
</template>

<script>
  export default {
    name: 'Modal',
    methods: {
      close() {
        this.$emit('close');
      },
    },
  };
</script>

<style>
  .modal-backdrop {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: table;
    transition: opacity 0.3s ease;
    opacity: 100%;

    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }

  .modal {
    width: 550px;
    height: 500px;
    margin-top: 10%;
    margin-left: 33%;
    padding: 20px 30px;
    background-color: rgb(0, 0, 0);
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
    display: flex;
    flex-direction: column;
    border-radius: 5px;
  }

  .modal-header,
  .modal-footer {
    padding: 15px;
    display: flex;
  }

  .modal-header {
    position: relative;
    background-color: rgb(112, 1, 1);
    border: none;
    color: rgb(255, 255, 255);
    font-size: 2em;
    justify-content: space-between;
  }

  .modal-footer {
    border-top: 1px solid #000000;
    flex-direction: column;
  }

  .modal-body {
    position: relative;
    color: #eeeeee;
    padding: 20px 10px;
    text-align: center;
    line-height: 200%;
  }
  .btn-close {
    position: absolute;
    top: 0;
    right: 0;
    border: none;
    font-size: 20px;
    padding: 10px;
    cursor: pointer;
    font-weight: bold;
    color: #ffffff;
    background: transparent;
  }

  .btn-green {
    color: black;
    background: white;
    font-size: 1em;
    font-weight: bold;
    border: 1px solid #ffffff;
    border-radius: 2px;
  }
  .btn-green:hover{
	background: black;
    color:white;
}

  .modal-fade-enter,
  .modal-fade-leave-to {
    opacity: 0;
  }

  .modal-fade-enter-active,
  .modal-fade-leave-active {
    transition: opacity .5s ease;
  }
</style>